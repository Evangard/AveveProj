package ui.pages;

import ui.WaitUtil;
import ui.config.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import ui.tools.AdditionalConditions;
import ui.tools.WebElementUtils;

import java.util.function.Supplier;

public abstract class AbstractPage {

    private WebElementUtils webElementUtils;

    protected AbstractPage() {
        PageFactory.initElements(Browser.getDriver(), this);
        waitUntilLoaded();
    }

    protected abstract void waitUntilLoaded();

    protected boolean waitUntil(Supplier<Boolean> condition) {
        return WaitUtil.getWebDriverWait().until(AdditionalConditions.isTrue(condition));
    }

    protected boolean waitUntil(Supplier<Boolean> condition, int seconds) {
        return WaitUtil.getWebDriverWait(seconds).until(AdditionalConditions.isTrue(condition));
    }

    protected boolean tryWaitUntil(Supplier<Boolean> condition) {
        try {
            return waitUntil(condition);
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean tryWaitUntil(Supplier<Boolean> condition, int seconds) {
        try {
            return waitUntil(condition, seconds);
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected WebElementUtils webElementUtils() {
        return webElementUtils != null ? webElementUtils : WebElementUtils.getInstance();
    }
}
