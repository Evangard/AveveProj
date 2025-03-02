package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.elements.Button;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class, 'hidden')]/a//span[text()='MyAveve']")
    private WebElement myAveveButton;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptAllCookiesButton;

    @FindBy(xpath = "//title[text()='Home - Aveve - hier groeit plezier']")
    private WebElement title;

    public Button myAveveButton() {
        return new Button(myAveveButton);
    }

    public Button acceptAllCookiesButton() {
        return new Button(acceptAllCookiesButton);
    }

    public LoginPage openLoginPage() {
        myAveveButton().click();
        return new LoginPage();
    }

    @Override
    protected void waitUntilLoaded() {
        waitUntil(() -> title.isEnabled());
        if (tryWaitUntil(() -> acceptAllCookiesButton.isDisplayed(), 5)) {
            acceptAllCookiesButton().click();
        }
    }
}
