import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests {
    WebDriver driver;

    LoginPage loginPage;
    NavBar navBar;
    String url = "https://www.automationexercise.com/";
    String  loginTextActual = "Login to your account";
    String  email = "enasshamia10@gmail.com";
    String  password = "123";
    @BeforeClass
    public void setUp()
    {
        driver=new ChromeDriver();
        loginPage=new LoginPage(driver);
        navBar=new NavBar(driver);
        driver.manage().window().maximize();
        loginPage.navigateToUrl(url);

    }
    @Test
    public void validateUserLoginSuccess(){
        navBar.navigateToLoginPage();
        loginPage.verifyUserOnLoginPage(loginTextActual);
       loginPage.fillLoginInformation(email ,password);
        loginPage.clickLoginButton();
        loginPage.verifyAccountIconVisible();
        }

        @AfterMethod
    public  void tearDown()
    {
        driver.quit();
    }


}
