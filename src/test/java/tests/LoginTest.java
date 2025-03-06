package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.pages.CustomerPage;
import ui.pages.HomePage;
import ui.pages.LoginPage;

import java.lang.reflect.Method;

public class LoginTest extends AbstractTest {

    private static final String EMAIL = "anton.mikolaenko@gmail.com";
    private static final String PASS = "TesteR_2025";
    private LoginPage loginPage;
    private HomePage homePage;
    private CustomerPage customerPage;

    @BeforeClass
    public void beforeActions() {
        homePage = AbstractTest.browser.openHomePage();
        loginPage = homePage.openLoginPage();
    }

    @Test(dataProvider = "userData")
    public void verifyUserCanLoginWithValidData(String email, String pass) {
        customerPage = loginPage.login(email, pass);
        Assert.assertTrue(customerPage.isCustomerPageShown(),"Customer page should be opened.");
    }

    @Test
    public void verifyErrorMessageIsShownIfUserSetWrongUserData() {
        loginPage.login(EMAIL, "asdasd");
        Assert.assertTrue(loginPage.isErrorMessageShown(),"Error message should be shown.");
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethodActions(Method method) {
        if (method.getName().equalsIgnoreCase("verifyUserCanLoginWithValidData")) {
            customerPage.logout();
            homePage.openLoginPage();
        }
    }

    @DataProvider(name = "userData")
    public static Object[][] userData() {
        return new Object[][]{
                {EMAIL, PASS},
                {EMAIL, "wrongPass"}
        };
    }

}
