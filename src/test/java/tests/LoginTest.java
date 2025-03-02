package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.pages.HomePage;
import ui.pages.LoginPage;

public class LoginTest extends AbstractTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private static final String USER = "anton.mikolaenko@gmail.com";
    private static final String PASS = "TesteR_2025";

    @BeforeClass
    public void beforeActions() {
//        homePage = AbstractTest.browser.openHomePage().login(USER, PASS);
    }

    @Test(dataProvider = "attempts")
    public void test1(int att) {

    }

    @DataProvider(name = "attempts")
    public static Object[][] attempts() {

        return new Object[][]{
                {1},
                {2}
        };
    }
}
