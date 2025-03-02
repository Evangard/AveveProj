package ui.pages;

import org.openqa.selenium.support.ui.ExpectedCondition;
import ui.WaitUtil;
import ui.config.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import ui.tools.AdditionalConditions;

import java.time.Duration;
import java.util.function.Supplier;

public abstract class AbstractPage {

    protected AbstractPage() {
        PageFactory.initElements(Browser.getDriver(), this);
        waitUntilLoaded();
    }

    protected abstract void waitUntilLoaded();

//    public static FluentWait<WebDriver> getWebDriverWait() {
//        return getWebDriverWait(DEFAULT_TIMEOUT_TO_WAIT);
//    }
//
//    public static FluentWait<WebDriver> getWebDriverWait(int timeInSeconds) {
//        return new FluentWait<>(Browser.getDriver()).withTimeout(Duration.ofSeconds(timeInSeconds))
//                .ignoring(NoSuchElementException.class)
//                .ignoring(StaleElementReferenceException.class)
//                .ignoring(ElementNotInteractableException.class);
//    }
//
//    protected boolean waitUntilElementIsVisible(WebElement element) {
//        return getWebDriverWait().until(ExpectedConditions.not(ExpectedConditions.invisibilityOf(element)));
//    }
//
//    protected boolean waitUntilElementIsVisible(WebElement element, int timeout) {
//        return getWebDriverWait(timeout).until(ExpectedConditions.not(ExpectedConditions.invisibilityOf(element)));
//    }

    public final void refreshCurrentPage() {
        Browser.getDriver().navigate().refresh();
    }

    protected boolean waitUntil(Supplier<Boolean> condition) {
        return WaitUtil.getWebDriverWait().until(AdditionalConditions.isTrue(condition));
    }
}
