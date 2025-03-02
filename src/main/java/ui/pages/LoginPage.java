package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.WaitUtil;
import ui.elements.Button;
import ui.elements.Input;
import ui.elements.Label;

public class LoginPage extends AbstractPage {

    @FindBy(id = "email-address")
    private WebElement emailAddressField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//h1[normalize-space()='Aanmelden']")
    private WebElement registerLabel;

    public Input emailAddressField() {
        return new Input(emailAddressField, "E-mailadres field");
    }

    public Input passwordField() {
        return new Input(passwordField, "Wachtwoord field");
    }

    public Button submitButton() {
        return new Button(submitButton, "Aanmelden button");
    }

    public Label registerLabel() {
        return new Label(registerLabel, "Aanmelden label");
    }

    @Step
    public HomePage login(String email, String password) {
        setEmail(email);
        setPassword(password);
        submitButton().click();
        return new HomePage();
    }

    @Step("Enter '{0}' email")
    public void setEmail(String email) {
        emailAddressField().clearAndType(email);
    }

    @Step("Enter '{0}' password")
    public void setPassword(String password) {
        passwordField().clearAndType(password);
    }

    @Override
    protected void waitUntilLoaded() {
        waitUntil(() -> registerLabel.isDisplayed());
    }
}
