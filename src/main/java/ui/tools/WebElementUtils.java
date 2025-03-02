package ui.tools;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.config.Browser;

import static ui.WaitUtil.getWebDriverWait;

/**
 * This class contains utility methods to work with WebElement objects
 */

public class WebElementUtils {
    private static WebElementUtils webElementUtils;

    private WebElementUtils() {
    }

    public static WebElementUtils getInstance() {
        if (webElementUtils == null) {
            webElementUtils = new WebElementUtils();
        }
        return webElementUtils;
    }

    public static boolean isElementShown(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException ignored) {
            return false;
        }
    }

    public static boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException | StaleElementReferenceException ignored) {
            return false;
        }
    }

    public void waitForClickable(WebElement element, int timeInSeconds) {
        try {
            getWebDriverWait(timeInSeconds).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOn(WebElement element) {
        try {
            element.click();
        } catch (WebDriverException ignored) {
            scrollIntoViewByCoordinate(element);
            element.click();
        }
    }

    public void clickJS(WebElement element) {
        getJavascriptExecutor().executeScript("arguments[0].click();", element);
    }

    public WebElement scrollIntoViewByCoordinate(WebElement e) {
        getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(true); scrollBy(0,-100);", e);
        return e;
    }

    public JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) Browser.getDriver();
    }
}