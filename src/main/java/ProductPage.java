import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v131.log.model.LogEntry;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Shortcut dropdownValidator ;

    // Locators
    private static final By PRODUCT_OVERLAY = By.className("product-overlay");
    private static final By PRODUCT_INFO = By.cssSelector(".features_items .productinfo");
    private static final By PRODUCT_TITLE = By.cssSelector("p");
    private static final By PRODUCT_PRICE = By.cssSelector("h2");
    private static final By PRODUCT_IMAGE = By.tagName("img");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector(".add-to-cart");
    private static final By ALERT_MODAL = By.cssSelector("div.modal-body p");
    private static final By CLOSE_ALERT_BUTTON = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
    private static final By searchInput = By.id("search_product");
    private static final By searchButton = By.id("submit_search");
    private static final By searchResults = By.cssSelector(".features_items .productinfo");
    private static final By noResultsMessage = By.cssSelector(".features_items p");
    private static final By categoryText = By.cssSelector(".col-sm-3 .left-sidebar h2:first-child");
    private static final By brandText = By.xpath("(//div[@class='brands_products']//h2)[1]");
    private static final By categoryList = By.cssSelector(".panel-heading  .panel-title  a");
    private static final By brandsList = By.cssSelector(".brands_products .brands-name li");
    private static final By WOMEN_CATEGORY=   By.xpath("//a[normalize-space()='Women']//i[@class='fa fa-plus']");
    private static final By womanSubCategory= By.cssSelector("#Women div ul li a");
   // private static final By Sub_Category= By.xpath("//div[@id='Women']//div[@class='panel-body']");
    private static final By Man_CATEGORY=   By.xpath("//a[normalize-space()='Men']//i[@class='fa fa-plus']");
    private static final By manSubCategory= By.cssSelector("#Men div ul li a");
    private static final By KIDS_CATEGORY=   By.xpath("//a[normalize-space()='Kids']//i[@class='fa fa-plus']");
    private static final By kidsSubCategory= By.cssSelector("#Kids div ul li a");
   // private static final By subMenu= By.id("Men");
    // Test data
    private static final int EXPECTED_PRODUCT_COUNT = 34;
    List<String> expectedCategories = Arrays.asList("WOMEN", "MEN", "KIDS");
    List<String> expectedBrands = Arrays.asList(
            "POLO",
            "H&M",
            "MADAME",
            "MAST & HARBOUR",
            "BABYHUG",
            "ALLEN SOLLY JUNIOR",
            "KOOKIE KIDS",
            "BIBA"
    );
    List<String> expectedWomanSubcategories = Arrays.asList(
            "DRESS", "TOPS", "SAREE");
    List<String> expectedManSubcategories = Arrays.asList(
            "TSHIRTS ", "JEANS");
    List<String> expectedKidsSubcategories = Arrays.asList(
            "DRESS ", "TOPS & SHIRTS");
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.dropdownValidator = new Shortcut(driver);

    }

    public void navigateToUrl(String url) {
        driver.navigate().to(url);
    }

    public ProductPage validateProductSize() {
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_OVERLAY));
        Assert.assertEquals(products.size(), EXPECTED_PRODUCT_COUNT, "Products count mismatch");
        return this;
    }

    public ProductPage validateProductsDetails() {
        List<WebElement> products = getVisibleProducts();

        for (WebElement product : products) {
            validateProductTitle(product);
            validateProductPrice(product);
            validateProductImage(product);
            validateAddToCartButton(product);
        }
        return this;
    }

    public ProductPage validateDisplayAlert() {
        List<WebElement> products = getVisibleProducts();

        for (WebElement product : products) {
            try {
                clickAddToCart(product);
                validateAndCloseAlert();
            } catch (Exception e) {
                System.err.println("Failed to validate alert for product: " + e.getMessage());
            }
        }
        return this;
    }

    // Private helper methods
    private List<WebElement> getVisibleProducts() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_INFO));
    }

    private void validateProductTitle(WebElement product) {
        WebElement title = product.findElement(PRODUCT_TITLE);
        String titleText = title.getText().trim();
        Assert.assertFalse(titleText.isEmpty(), "Product title is missing");
        Assert.assertTrue(title.isDisplayed(), "Product title not visible");
    }

    private void validateProductPrice(WebElement product) {
        WebElement price = product.findElement(PRODUCT_PRICE);
        String priceText = price.getText().trim();
        Assert.assertFalse(priceText.isEmpty(), "Product price is missing");
        Assert.assertTrue(priceText.matches(".*\\d.*"), "Price doesn't contain numbers");
    }

    private void validateProductImage(WebElement product) {
        WebElement image = product.findElement(PRODUCT_IMAGE);
        Assert.assertTrue(image.isDisplayed(), "Product image is missing");
        Assert.assertFalse(image.getAttribute("src").isEmpty(), "Image source is empty");
    }

    private void validateAddToCartButton(WebElement product) {
        WebElement addCartButton = product.findElement(ADD_TO_CART_BUTTON);
        Assert.assertTrue(addCartButton.isDisplayed(), "Add to cart button is missing");
        Assert.assertTrue(addCartButton.isEnabled(), "Add to cart button is disabled");
    }

    private void clickAddToCart(WebElement product) {
        product.findElement(ADD_TO_CART_BUTTON).click();
    }

    private void validateAndCloseAlert() {
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(ALERT_MODAL));
        WebElement closeButton = driver.findElement(CLOSE_ALERT_BUTTON);

        Assert.assertTrue(closeButton.isDisplayed(), "Close button is missing");
        Assert.assertTrue(closeButton.isEnabled(), "Close button is disabled");

        closeButton.click();
        wait.until(ExpectedConditions.invisibilityOf(alert));
    }

    public ProductPage validateSearchFunctionality(String searchTerm, int expectedMinResults, String description) {
        System.out.println("Testing: " + description);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInput));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(searchTerm);
        driver.findElement(searchButton).click();

        if (expectedMinResults > 0) {
            List<WebElement> results = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResults));
            Assert.assertTrue(results.size() >= expectedMinResults,
                    "Expected at least " + expectedMinResults + " results for: " + searchTerm);
            System.out.println(results.size() + " results found for: " + searchTerm);
        } else {
            try {
                WebElement message = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(noResultsMessage));
                Assert.assertTrue(
                        message.getText().toLowerCase().contains("no results") ||
                                message.getText().isEmpty(),
                        "Expected no results message");

            } catch (TimeoutException e) {
                List<WebElement> results = driver.findElements(searchResults);
                Assert.assertEquals(results.size(), 0,
                        "Expected no results but found " + results.size());
            }
        }

        return this;
    }

    public ProductPage validateCategoryText() {
        WebElement category = driver.findElement(categoryText);
        String categoryActual = category.getText().trim();
        Assert.assertEquals(categoryActual, "CATEGORY", "Category is missing");
        Assert.assertTrue(category.isDisplayed(), "Category is not  displayed");
        return this;

    }

    public ProductPage validateBrandsText() {
        WebElement brands = wait.until(ExpectedConditions.visibilityOfElementLocated(brandText));
        String brandActual = brands.getText().trim();
        Assert.assertEquals(brandActual, "BRANDS", "brands is missing");
        Assert.assertTrue(brands.isDisplayed(), "Brands is not  displayed");
        return this;

    }

    public ProductPage validateCategoryList() {
        List<WebElement> categoryElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(categoryList));
        Assert.assertEquals(categoryElements.size(), expectedCategories.size(), "Number of main categories mismatch");
        for (int i = 0; i < expectedCategories.size(); i++) {
            String actualCategory = categoryElements.get(i).getText().trim();
            Assert.assertEquals(actualCategory, expectedCategories.get(i), "Main category text mismatch at index " + i);
            Assert.assertTrue(categoryElements.get(i).isEnabled(), "category not at index " + i);


        }

        return this;
    }

    public ProductPage validateBrandsList() {
        List<WebElement> brandElements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(brandsList));
        Assert.assertEquals(brandElements.size(), expectedBrands.size(), "Number of main brands mismatch");
        for (int i = 0; i < expectedBrands.size(); i++) {
            String actualText = brandElements.get(i).getText()
                    .replaceAll("\\(\\d+\\)", "")
                    .trim();
            ;
            String expectedText = expectedBrands.get(i);

            Assert.assertEquals(actualText, expectedText,
                    String.format("Brand mismatch at index %d. Expected: '%s', Actual: '%s'",
                            i, expectedText, actualText));
            Assert.assertTrue(brandElements.get(i).isEnabled(), "Brand not at index " + i);
        }
        return this;

    }


    public ProductPage validateWomanDropDownList() {
        dropdownValidator.validateCategoryDropdown(
                WOMEN_CATEGORY,
                womanSubCategory,
                "Women",
                expectedWomanSubcategories
        );
        return this;
    }

    public ProductPage validateMenDropDownList() {
        dropdownValidator.validateCategoryDropdown(
                Man_CATEGORY,
                manSubCategory,
                "Men",
                expectedManSubcategories
        );
        return this;
    }
    public ProductPage validateKidsDropDownList() {
        dropdownValidator.validateCategoryDropdown(
                KIDS_CATEGORY,
                kidsSubCategory,
                "Kids",
                expectedKidsSubcategories
        );
        return this;
    }

}

