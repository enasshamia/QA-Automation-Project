import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    WebDriver driver;
    public LoginPage( WebDriver driver)
    {
        this.driver=driver;
    }
    //Locators
    private  By loginPageText = By.xpath("//div[@class=\"login-form\"]//h2");
    private  By emailTextField = By.xpath("//input[@data-qa='login-email']");
    private  By PasswordTextField = By.xpath("//input[@placeholder='Password']");
    private  By loginButton = By.xpath("//button[normalize-space()='Login']");
    private By accountIcon = By.xpath("//b[normalize-space()='enas test']");

    //Actions
public void navigateToUrl (String url){
    driver.navigate().to(url);
}

    public void fillEmailAddress (String email){
        driver.findElement(emailTextField).sendKeys(email);
    }
    public void fillPassword (String password){
        driver.findElement(PasswordTextField).sendKeys(password);
    }
    public void fillLoginInformation (String email , String password){
       fillEmailAddress(email);
       fillPassword(password);
    }
    public void clickLoginButton (){
        driver.findElement(loginButton).click();
    }
    //Assertion
    public void verifyUserOnLoginPage (String loginTextExpected){
    String loginTextActual = driver.findElement(loginPageText).getText();
    Assert.assertEquals(loginTextActual , loginTextExpected);
}
public void verifyAccountIconVisible(){
    boolean accountIconVisibility = driver.findElement(accountIcon).isDisplayed();
    Assert.assertTrue(accountIconVisibility);

}
}


