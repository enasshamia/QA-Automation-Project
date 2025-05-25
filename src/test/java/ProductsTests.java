import org.openqa.selenium.By;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class ProductsTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private ProductPage productPage;
    private LoginPage loginPage;
    private NavBar navBar;

    // Constants
    private static final String BASE_URL = "https://www.automationexercise.com/";
    private static final By CART_MODAL_CLOSE_BUTTON = By.cssSelector("div#cartModal button.close");
    private static final By CART_MODAL = By.id("cartModal");

    @BeforeClass
    public void setUp() {
        initializeDriver();
        initializePageObjects();
        maximizeWindow();
        navigateToBaseUrl();
        closeModalIfPresent();
    }

    @Test(priority = 1)
    public void validateNavigateUserProducts() {
        navBar.navigateToProductsPage();
    }

    @Test(priority = 2)
    public void validateProductsSizeTest() {
        productPage.validateProductSize();
    }

    @Test(priority = 3)
    public void validateProductsDetailsTest() {
        productPage.validateProductsDetails();
    }

   // @Test(priority = 4)
    public void validateDisplayAlertTest() {
        productPage.validateDisplayAlert();
    }
   //@Test(dataProvider = "searchData", dataProviderClass = DataSearch.class ,priority = 5)

    public void validateSearchProduct(String searchTerm, int expectedMinResults, String descriptionS) {
        productPage.validateSearchFunctionality( searchTerm,  expectedMinResults,  descriptionS);
    }
    @Test(priority = 6)
    public void validateCategoryAndBrandsTest() {
        productPage.validateCategoryText().validateBrandsText().validateCategoryList().
                validateBrandsList().validateWomanDropDownList().validateMenDropDownList().validateKidsDropDownList();

    }
    @AfterClass
    public void tearDown() {
        quitDriver();
    }

    // Private helper methods
    private void initializeDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void initializePageObjects() {
        productPage = new ProductPage(driver);
        loginPage = new LoginPage(driver);
        navBar = new NavBar(driver);
    }

    private void maximizeWindow() {
        driver.manage().window().maximize();
    }

    private void navigateToBaseUrl() {
        productPage.navigateToUrl(BASE_URL);
    }

    private void closeModalIfPresent() {
        try {
            driver.findElement(CART_MODAL_CLOSE_BUTTON).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(CART_MODAL));
        } catch (Exception e) {
            // Modal not present - continue
        }
    }

    private void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}