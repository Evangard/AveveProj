package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.config.Browser;
import ui.elements.Button;
import ui.tools.enums.Tabs;

public class HomePage extends GenericPage {

    @FindBy(xpath = "//div[contains(@class, 'hidden')]/a//span[text()='MyAveve']")
    private WebElement myAveveButton;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptAllCookiesButton;

    @FindBy(xpath = "//title[text()='Home - Aveve - hier groeit plezier']")
    private WebElement title;

    @FindBy(xpath = "//div[@id='desktop-nav']//a[normalize-space()='Tuin']")
    private WebElement tuinTab;

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
