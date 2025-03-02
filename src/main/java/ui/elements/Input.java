package ui.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ui.AbstractGuiElement;
import ui.WaitUtil;

public class Input extends AbstractGuiElement {

	private static final String SHOULD_BE_SHOWN_BEFORE_TYPING = "%s should be shown before typing.";
	public Input(WebElement element) {
		super(element);
	}

	public Input(WebElement element, String name) {
		super(element, name);
	}

	public void sendKeys(CharSequence... keysToSend) {
		getWrappedElement().sendKeys(keysToSend);
	}

	public void type(String text) {
		waitUntil(()-> getWrappedElement().isDisplayed());
		waitUntil(this::isClickable);
		getWrappedElement().click();
		sendKeys(text);
	}

	public void clear() {
		try {
			waitForClickable();
        } catch (TimeoutException | NoSuchElementException e) {
            throw new IllegalStateException(String.format(SHOULD_BE_SHOWN_BEFORE_TYPING, super.name), e);
        }
		getWrappedElement().clear();
	}

	public void clearAndType(String text) {
		try {
			waitForClickable();
		} catch (TimeoutException | NoSuchElementException e) {
			throw new IllegalStateException(String.format(SHOULD_BE_SHOWN_BEFORE_TYPING, super.name), e);
		}
		clear();
		type(text);
	}

	@Step("Check input is clickable")
	public boolean isClickable() {
		return isShown() && isEnabled();
	}

	@Step("Wait for input clickable in '{0}' seconds")
	public Input waitForClickable(int timeInSeconds) {
		WaitUtil.waitUntilCondition(this::isClickable, "clickable", timeInSeconds);
		return this;
	}

	@Step("Wait for input clickable.")
	public Input waitForClickable() {
		return waitForClickable(WaitUtil.DEFAULT_TIMEOUT_TO_WAIT);
	}
}
