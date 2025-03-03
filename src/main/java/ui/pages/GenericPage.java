package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.config.Browser;
import ui.tools.WebElementUtils;
import ui.tools.enums.Tabs;

import static ui.WaitUtil.waitForSeconds;

public abstract class GenericPage extends AbstractPage {

    @FindBy(xpath = "//div[starts-with(@class, 'flex flex-row') and normalize-space()='Loading...']")
    private WebElement spinner;

    @Step("Click on '{0}' tab.")
    public void openTab(Tabs tab) {
        Browser.getDriver().findElement(By.xpath(
                String.format("//div[@id='desktop-nav']//a[normalize-space()='%s']", tab.getName()))).click();
    }

    @Step("Select '{0}' category")
    public void selectCategory(String categoryName) {
        String locator = String.format("//div[starts-with(@class, 'flex flex-wrap')]//a[text()='%s']", categoryName);
        waitUntil(() -> Browser.getDriver().findElement(By.xpath(locator)).isDisplayed());
        webElementUtils().clickJS(Browser.getDriver().findElement(By.xpath(locator)));
    }

    @Step("Add item to the cart.")
    public void addFirstItemToCart() {
        String locator = "//div[starts-with(@class, 'item product')][2]//button[@title='Add to Cart']";
        waitUntil(() -> Browser.getDriver().findElement(By.xpath(locator)).isDisplayed());
        WebElement addToCartButton = Browser.getDriver().findElement(By.xpath(locator));
        webElementUtils().scrollIntoElement(addToCartButton);
        waitForSeconds(1);
        webElementUtils().clickJS(addToCartButton);
    }

    @Step("Navigate to the order")
    public CheckoutPage openOrderPage() {
        String locator = "//div[@id='cart-drawer-dialog']//a[normalize-space()='Naar bestellen']";
        tryWaitUntil(() -> Browser.getDriver().findElement(By.xpath(locator)).isDisplayed());
        WebElement toOrderButton = Browser.getDriver().findElement(By.xpath(locator));
        webElementUtils().clickJS(toOrderButton);
        return new CheckoutPage();
    }

    public void waitUntilLoadingSpinnerAppear() {
        tryWaitUntil(() -> spinner.isDisplayed());
    }

    public void waitUntilLoadingSpinnerDisappear() {
        tryWaitUntil(() -> !spinner.isDisplayed());
    }

    public void waitSpinner() {
        waitUntilLoadingSpinnerAppear();
        waitUntilLoadingSpinnerDisappear();
    }
}
