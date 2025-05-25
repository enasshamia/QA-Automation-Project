import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavBar {

    WebDriver driver;
    public NavBar( WebDriver driver)
    {
        this.driver=driver;
    }

    private  By navBarLoginButton = By.xpath("//a[@href=\"/login\"]");
    private By navBarProductsButton = By.xpath("//a[@href='/products']");
    private By navBarProductsDetails = By.xpath("//div[3]//div[1]//div[2]//ul[1]//li[1]//a[1]");

    public NavBar navigateToLoginPage (){
        driver.findElement(navBarLoginButton).click();
        return this;
    }
    public NavBar navigateToProductsPage (){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(navBarProductsButton)).click();

        driver.findElement(navBarProductsButton).click();
        return this;
    }
    public NavBar navigateToProductsDetailsPage (){
        driver.findElement(navBarProductsDetails).click();
        return this;
    }
}
