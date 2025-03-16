package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.config.Browser;
import ui.elements.Button;
import ui.elements.Input;
import ui.tools.enums.DeliveryType;
import ui.tools.enums.PaymentType;

public class CheckoutPage extends GenericPage {

    @FindBy(xpath = "//div[@class='cart-summary']//a[@id='checkout-link-button']")
    private WebElement orderAndPayButton;

    @FindBy(xpath = "//button[normalize-space()='Volgende']")
    private WebElement nextButton;

    @FindBy(xpath = "//div[@class='control qty']//input")
    private WebElement qtyField;

    @FindBy(xpath = "//div[@id='card-form']//button[@id='submit-button']")
    private WebElement payButton;

    public Button payButton() {
        return new Button(payButton, "Betaal button");
    }

    public Input qtyField() {
        return new Input(qtyField, "Quantity field");
    }

    public Button orderAndPayButton() {
        return new Button(orderAndPayButton, "Bestellen en betalen button");
    }

    public Button nextButton() {
        return new Button(nextButton, "Volgende button");
    }

    public boolean isCheckoutPageOpened() {
        return Browser.getDriver().getTitle().equals("Shopping Cart");
    }

    @Step("Set quantity: '{0}'.")
    public CheckoutPage setQuantity(int amount) {
        waitUntil(() -> qtyField.isDisplayed());
        qtyField.sendKeys(Keys.CONTROL, "a");
        qtyField.sendKeys(String.valueOf(amount));
        qtyField.sendKeys(Keys.ENTER);
        waitSpinner();
        return this;
    }

    @Step("Select '{0}' delivery type.")
    public CheckoutPage selectDeliveryType(DeliveryType type) {
        String locator = String.format("//div[normalize-space()='%s']/input", type.getName());
        waitUntil(() -> Browser.getDriver().findElement(By.xpath(locator)).isDisplayed(), 10);
        Browser.getDriver().findElement(By.xpath(locator)).click();
        waitSpinner();
        return this;
    }

    @Step("Click 'Order And Pay' button")
    public CheckoutPage clickOnOrderAndPayButton() {
        orderAndPayButton().click();
        return this;
    }

    @Step("Click 'Next' button")
    public CheckoutPage clickNextButton() {
        waitUntil(() -> nextButton.isDisplayed(), 10);
        nextButton().click();
        return this;
    }

    @Step("Select '{0}' payment type.")
    public CheckoutPage selectPaymentType(PaymentType type) {
        String locator = String.format("//div[normalize-space()='%s']/ancestor::li//input", type.getName());
        waitUntil(() -> Browser.getDriver().findElement(By.xpath(locator)).isDisplayed(), 10);
        Browser.getDriver().findElement(By.xpath(locator)).click();
        waitSpinner();
        return this;
    }

    @Step
    public boolean isPayButtonShown() {
        return tryWaitUntil(() -> payButton.isDisplayed());
    }

    @Override
    protected void waitUntilLoaded() {
        waitUntil(this::isCheckoutPageOpened);
    }
}
