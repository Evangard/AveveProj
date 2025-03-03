package ui;

import io.qameta.allure.Step;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import ui.config.Browser;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public final class WaitUtil {

    public static final int DEFAULT_TIMEOUT_TO_WAIT = 5;

    public static FluentWait<WebDriver> getWebDriverWait(int timeInSeconds) {
        return new FluentWait<>(Browser.getDriver()).withTimeout(Duration.ofSeconds(timeInSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static FluentWait<WebDriver> getWebDriverWait() {
        return getWebDriverWait(DEFAULT_TIMEOUT_TO_WAIT);
    }

    @Step("Waiting for {2} seconds until the element condition will be true")
    public static void waitUntilCondition(Supplier<Boolean> condition, String conditionName, int timeInSeconds) {
        try {
            Function<WebDriver, Boolean> conditionToBeTrue = (WebDriver d) -> condition.get().equals(Boolean.TRUE);
            WaitUtil.getWebDriverWait(timeInSeconds).until(conditionToBeTrue);
        } catch (Exception e) {
            RuntimeException exception = new RuntimeException(String.format(
                    "Element is still not '%s' after %d seconds of wait. Original error message: %s",
                    conditionName, timeInSeconds, e.getMessage()));
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static void waitForSeconds(long seconds) {
        long millis = TimeUnit.MILLISECONDS.convert(seconds, TimeUnit.SECONDS);
        waitFor(millis);
    }

    public static void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
