package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.config.Browser;
import ui.tools.enums.Tabs;

public abstract class GenericPage extends AbstractPage {

    @FindBy(xpath = "//div[starts-with(@class, 'flex flex-row') and normalize-space()='Loading...']")
    private WebElement spinner;

    public void openTab(Tabs tab) {
        Browser.getDriver().findElement(By.xpath(
                String.format("//div[@id='desktop-nav']//a[normalize-space()='%s']", tab.getName()))).click();
    }

    public void selectCategory(String categoryName) {
        Browser.getDriver().findElement(By.xpath(
                String.format("//a[normalize-space()='%s']", categoryName))).click();
    }

    public void addFirstItemToCart() {
        Browser.getDriver().findElement(By.xpath(
                "//div[starts-with(@class, 'item product')][1]//button[@title='Add to Cart']")).click();
    }

    public CheckoutPage openOrderPage() {
        String locator = "//div[@id='cart-drawer-dialog']//a[normalize-space()='Naar bestellen']";
        tryWaitUntil(() -> Browser.getDriver().findElement(By.xpath(locator)).isDisplayed());
        Browser.getDriver().findElement(By.xpath(locator)).click();
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
