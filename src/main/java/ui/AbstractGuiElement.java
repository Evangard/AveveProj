package ui;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.tools.WebElementUtils;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractGuiElement implements GuiElement {
    private final SearchContext element;
    protected String name;

    protected AbstractGuiElement(WebElement element) {
        this.element = element;
    }

    protected AbstractGuiElement(WebElement element, String name) {
        this.element = element;
        this.name = name;
    }
    @Override public WebElement getWrappedElement() {
        return (WebElement) element;
    }

    @Override
    public boolean isShown() {
        return WebElementUtils.isElementShown(getWrappedElement());
    }

    @Override
    public boolean isEnabled() {
        return WebElementUtils.isElementShown(getWrappedElement());
    }

    @Override
    public void click() {
        try {
            WaitUtil.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(getWrappedElement()));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new IllegalStateException("\n" + this.name + " should be clickable before click.\n", e);
        }
        try {
            WebElementUtils.getInstance().clickOn(getWrappedElement());
        } catch (WebDriverException ignored) {
            WebElementUtils.getInstance().clickJS(getWrappedElement());
        }
    }

    protected void waitUntil(Supplier<Boolean> condition) {
        Function<WebDriver, Boolean> conditionToBeTrue = (WebDriver d) -> condition.get().equals(Boolean.TRUE);
        WaitUtil.getWebDriverWait().until(conditionToBeTrue);
    }
}
