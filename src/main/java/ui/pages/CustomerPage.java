package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.elements.Button;
import ui.elements.Input;
import ui.elements.Label;

public class CustomerPage extends GenericPage {

    @FindBy(xpath = "//title[text()='My Account']")
    private WebElement title;

    @FindBy(xpath = "//*[text()='Uitloggen']/ancestor::a")
    private WebElement logoutButton;

    @FindBy(xpath = "//span[text()='Gelukt! Je bent veilig afgemeld.']")
    private WebElement successfulLogoutMessage;

    @FindBy(xpath = "//h2[starts-with( normalize-space(), 'Hallo')]")
    private WebElement greetingLabel;

    public Button logoutButton() {
        return new Button(logoutButton, "Uitloggen button");
    }

    @Step
    public boolean isCustomerPageShown() {
        return tryWaitUntil(() -> title.isEnabled() && greetingLabel.isDisplayed());
    }

    @Step
    public CustomerPage logout() {
        tryWaitUntil(() -> logoutButton.isDisplayed());
        logoutButton().click();
        if (!isCustomerLoggedOut()) {
            throw new IllegalStateException("Customer wasn't logget out.");
        };
        return this;
    }

    @Step
    public boolean isCustomerLoggedOut() {
        return tryWaitUntil(() -> successfulLogoutMessage.isDisplayed());
    }

    @Override
    protected void waitUntilLoaded() {
        isCustomerPageShown();
    }
}
