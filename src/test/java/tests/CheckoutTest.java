package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.CheckoutPage;
import ui.pages.CustomerPage;
import ui.pages.HomePage;
import ui.tools.enums.DeliveryType;
import ui.tools.enums.PaymentType;
import ui.tools.enums.Tabs;

import java.lang.reflect.Method;

public class CheckoutTest extends AbstractTest {

    private static final String EMAIL = "anton.mikolaenko@gmail.com";
    private static final String PASS = "TesteR_2025";
    private HomePage homePage;
    private CustomerPage customerPage;

    @BeforeClass
    public void beforeActions() {
        homePage = AbstractTest.browser.openHomePage();
    }

    @Test
    public void verifyUserCanLoginWithValidData() {
        homePage.openLoginPage()
                .login(EMAIL, PASS);
        homePage.openTab(Tabs.DIER);
        homePage.selectCategory("Hond");
        homePage.addFirstItemToCart();
        CheckoutPage checkoutPage = homePage.openOrderPage()
                .setQuantity(2)
                .clickOnOrderAndPayButton()
                .selectDeliveryType(DeliveryType.CLICK_AND_COLLECT)
                .clickNextButton()
                .selectPaymentType(PaymentType.CREDIT_CARDS)
                .clickNextButton();
        Assert.assertTrue(checkoutPage.isPayButtonShown(),"Pay button should be displayed.");
    }
}
