package ui.pages;

import ui.config.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.elements.Button;

public class HomePage extends AbstractPage{

    @FindBy(xpath = "//div[contains(@class, 'hidden')]/a//span[text()='MyAveve']")
    private WebElement myAveveButton;

    public Button myAveveButton() {
        return new Button(myAveveButton);
    }

    public LoginPage openLoginPage() {
        myAveveButton().click();
        return new LoginPage();
    }

    @Override
    protected void waitUntilLoaded() {
//        waitUntilElementIsVisible(personAccountLink);
    }

//    public boolean isUserLoggedIn() {
//        return personAccountLink.isDisplayed();
//    }
//
//    public boolean isPersonAccountLink() {
//        return personAccountLink.isDisplayed();
//    }

}
