import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Shortcut {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Shortcut(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void validateCategoryDropdown(
            By categoryLocator,
            By subcategoryLocator,
            String panelId,
            List<String> expectedSubcategories) {

        try {
            // 1. Click the category
            WebElement category = wait.until(ExpectedConditions.elementToBeClickable(categoryLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", category);

            // 2. Wait for panel to expand
            wait.until(driver -> {
                WebElement panel = driver.findElement(By.id(panelId));
                return panel.getAttribute("class").contains("in") && panel.isDisplayed();
            });

            // 3. Validate subcategories
            List<WebElement> subCategoryElements = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(subcategoryLocator));

            Assert.assertEquals(subCategoryElements.size(), expectedSubcategories.size(),
                    String.format("Number of subcategories mismatch for %s. Expected %d, found %d",
                            panelId, expectedSubcategories.size(), subCategoryElements.size()));

            for (int i = 0; i < expectedSubcategories.size(); i++) {
                String actual = subCategoryElements.get(i).getText().trim();
                String expected = expectedSubcategories.get(i).trim();

                Assert.assertEquals(actual, expected,
                        String.format("%s subcategory mismatch at index %d. Expected: '%s', Actual: '%s'",
                                panelId, i, expected, actual));

                Assert.assertTrue(subCategoryElements.get(i).isEnabled(),
                        String.format("%s subcategory not enabled at index %d ('%s')",
                                panelId, i, expected));
            }

        } catch (Exception e) {
           // takeScreenshot("dropdown-error-" + panelId);
            throw new AssertionError(panelId + " dropdown validation failed", e);
        }
    }



}
