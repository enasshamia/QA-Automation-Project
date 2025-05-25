import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SignupPage {

    WebDriver driver;
    public SignupPage( WebDriver driver)
    {
        this.driver=driver;
    }
    //Locators
    private  By signupPageText = By.xpath("//div[@class=\"signup-form\"]//h2");
    private  By nameTextField = By.xpath("//input[@data-qa='signup-name']");
    private  By emailTextField = By.xpath("//input[@data-qa='signup-email']");
    private  By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By accountInformation = By.xpath("//*[contains(text(),'Enter Account Information')]");
    private  By titleText = By.xpath("//label[contains(text(),'Title')]");
    private  By nameText = By.xpath("//label[contains(text(),'Name')]");
    private  By emailText = By.xpath("//label[contains(text(),'Email')]");
    private  By passwordText = By.xpath("//label[contains(text(),'Password')]");
    private  By dobText = By.xpath("//label[contains(text(),'Date of Birth')]");
    private  By addressInformation = By.xpath("//b[normalize-space()='Address Information']");
    private  By firstNameText = By.xpath("//label[contains(text(),'First name')]");
    private  By lastNameText = By.xpath("//label[contains(text(),'Last name')]");
    private  By companyText = By.xpath("//label[contains(text(),'Company')]");
    private  By addressText = By.xpath("//label[contains(text(),'Address')]");
    private  By address2Text = By.xpath("//label[contains(text(),'Address 2')]");
    private  By countryText = By.xpath("//label[contains(text(),'Country')]");
    private  By stateText = By.xpath("//label[contains(text(),'State')]");
    private  By cityText = By.xpath("//label[contains(text(),'City')]");
    private  By codeText = By.xpath("//label[contains(text(),'Zipcode')]");
    private  By mnText = By.xpath("//label[contains(text(),'Mobile Number')]");
private  By msGender = By.xpath("//input[@id='id_gender1']");
private By mrsGender = By.xpath("//input[@id='id_gender2']");
private By nameField = By.xpath("//input[@id='name']");
private By passwordField = By.xpath("//input[@id='password']");
private By emailField = By.xpath("//input[@id='email']");
private By daysField = By.xpath("//select[@id='days']");
private By monthsField = By.xpath("//select[@id='months']");
private By yearsField = By.xpath("//select[@id='years']");
private By SignupCheckBox = By.xpath("//label[@for='newsletter']");
private By reciveCheckBox = By.xpath("//input[@id='optin']");
private By firstNameField = By.xpath("//input[@id='first_name']");
private By lastNameField = By.xpath("//input[@id='last_name']");
private By companyField = By.xpath("//input[@id='company']");
private By addressField = By.xpath("//input[@id='address1']");
private By address2Field = By.xpath("//input[@id='address2']");
private By countryField = By.xpath("//select[@id='country']");
private By stateField = By.xpath("//input[@id='state']");
private By cityField = By.xpath("//input[@id='city']");
private By codeField = By.xpath("//input[@id='zipcode']");
private By mnField = By.xpath("//input[@id='mobile_number']");
private By creatAccountButton = By.xpath("//button[@data-qa='create-account']");


    public  SignupPage navigateToUrl (String url){
        driver.navigate().to(url);
        return this;
    }

    public SignupPage fillNameField (String name){
        driver.findElement(nameTextField).sendKeys(name);
        return  this;
    }
    public SignupPage fillEmailAddress (String email){
        driver.findElement(emailTextField).sendKeys(email);
        return  this;
    }
    public SignupPage fillSignupInformation (String name , String email){
        fillNameField(name);
        fillEmailAddress(email);
        return  this;
    }
    public SignupPage clickSignupButton (){
        driver.findElement(signupButton).click();
        return  this;

    }
    public SignupPage selectMsGender ()  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement titleMr = wait.until(ExpectedConditions.elementToBeClickable(msGender));
        titleMr.click();


        return  this;
    }
    public SignupPage selectMrsGender () throws InterruptedException {
        Thread.sleep(2000);  // Wait 2 seconds (not recommended for large tests)

        driver.findElement(mrsGender).click();
        return  this;
    }
    public SignupPage selectNewsletterCheckbox (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement newsletter = wait.until(ExpectedConditions.elementToBeClickable(SignupCheckBox));
        newsletter.click();

        return  this;
    }
    public SignupPage selectOptionCheckbox(){
        driver.findElement(reciveCheckBox).click();
        return  this;
    }
    public SignupPage selectDayValue(String day){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement daysDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(daysField));
        daysDropdown.sendKeys(day);
        return  this;
    }
    public SignupPage selectMonthValue(String month){
        driver.findElement(monthsField).sendKeys(month);
        return  this;
    }
    public SignupPage selectYearValue(String year){
        driver.findElement(yearsField).sendKeys(year);
        return  this;
    }
    public SignupPage fillPasswordField (String password){
        driver.findElement(passwordField).sendKeys(password);
        return  this;
    }
    public SignupPage fillFirstnameField (String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
        return  this;
    }
    public SignupPage fillLastnameField (String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
        return  this;

    }
    public SignupPage fillCompanyField (String company){
        driver.findElement(companyField).sendKeys(company);
        return  this;

    }
    public SignupPage fillAddressField (String address){
        driver.findElement(addressField).sendKeys(address);
        return  this;
    }
    public SignupPage fillAddress2Field (String address2){
        driver.findElement(address2Field).sendKeys(address2);
        return  this;

    }
    public SignupPage fillCountryField (String country){
        WebElement countryDropdown=   driver.findElement(countryField);
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(country);
        return  this;
    }
    public SignupPage fillStateField (String state){
        driver.findElement(stateField).sendKeys(state);
        return  this;
    }
    public SignupPage fillCityField (String city){
        driver.findElement(cityField).sendKeys(city);
        return  this;
    }
    public SignupPage fillCodeField (String code){
        driver.findElement(codeField).sendKeys(code);
        return  this;
    }
    public SignupPage fillMobileNumberField (String mobileNumber){
        driver.findElement(mnField).sendKeys(mobileNumber);
        return  this;
    }
    public SignupPage clickCreateAccountButton (){
        driver.findElement(creatAccountButton).click();
        return  this;
    }
public  SignupPage fillAccountInformation(String day, String month , String year , String password, String firstName, String lastName,
String company , String address, String country, String state, String city, String code, String mobileNumber
)  {
   selectMsGender();
    fillPasswordField(password);
selectDayValue(day);
selectMonthValue(month);
selectYearValue(year);
//selectNewsletterCheckbox();
fillFirstnameField(firstName);
fillLastnameField(lastName);
fillCompanyField(company);
fillAddressField(address);
fillCountryField(country);
fillStateField(state);
fillCityField(city);
fillCodeField(code);
fillMobileNumberField(mobileNumber);

    return  this;
}
    //Assertion
    public SignupPage verifyUserOnSignupPage (String SignupTextExpected){
        String signupTextActual = driver.findElement(signupPageText).getText();
        Assert.assertEquals(signupTextActual , SignupTextExpected);
        return  this;
    }
public SignupPage verifyAccountInformationVisible (){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement accountInfoText = wait.until(ExpectedConditions.elementToBeClickable(accountInformation));


        Assert.assertTrue(accountInfoText.isDisplayed());
        return  this;
}
public SignupPage verifyAllLabelVisible(){
    List<WebElement> elements = Arrays.asList(
            driver.findElement(titleText),
            driver.findElement(nameText),
            driver.findElement(emailText),
            driver.findElement(passwordText),
            driver.findElement(dobText),
            driver.findElement(addressInformation),
            driver.findElement(firstNameText),
            driver.findElement(lastNameText),
            driver.findElement(companyText),
            driver.findElement(addressText),
            driver.findElement(address2Text),
            driver.findElement(countryText),
            driver.findElement(stateText),
            driver.findElement(cityText),
            driver.findElement(codeText),
            driver.findElement(mnText)
            );
    for (WebElement element : elements) {
        System.out.println("Element displayed: " + element.isDisplayed());
    }

    return this;
}
}
