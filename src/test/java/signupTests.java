import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class signupTests {
    WebDriver driver;
    SignupPage signupPage;
NavBar navBar;
    String url = "https://www.automationexercise.com/";
    String signupTextActual = "New User Signup!";
    String  name = "enas";
    String email = "enasshami009@gmail.com";
    String day = "5";
    String month = "12";
    String year = "2020";
    String password = "123";
    String firstname = "enas";
    String lastname = "shamia";
    String  company = "rr";
    String address = "w";
    String country = "Canada";
    String state = "ee";
    String city = "ffd";
    String code = "343";
    String mobileNumber = "345665656";

    @BeforeClass
    public void setUp()
    {
        driver=new ChromeDriver();
        signupPage=new SignupPage(driver);
        navBar=new NavBar(driver);
      driver.manage().window().maximize();
        signupPage.navigateToUrl(url);

    }
    @Test(priority=1)
    public void validateUserSignupSuccess(){
        navBar.navigateToLoginPage();
        signupPage.verifyUserOnSignupPage(signupTextActual).
                fillSignupInformation(name,email)
                .clickSignupButton();
    }
    @Test(priority=2)
    public  void validateSignupSuccess() {
         signupPage.selectMsGender().fillPasswordField(password).selectDayValue(day).selectMonthValue(month).selectYearValue(year).
                 fillAddressField(address).fillAddress2Field(address).fillCompanyField(company).selectNewsletterCheckbox().selectOptionCheckbox()
                 .fillCityField(city).fillCodeField(code).fillCountryField(country).fillStateField(state).fillFirstnameField(firstname).
                 fillLastnameField(lastname).fillMobileNumberField(mobileNumber).clickCreateAccountButton();
    }
    @AfterClass
    public  void tearDown()
    {
        driver.quit();
    }

}
