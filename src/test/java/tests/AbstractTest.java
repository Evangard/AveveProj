package tests;

import ui.config.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class AbstractTest {
    public SoftAssert soft;

    @AfterSuite(alwaysRun = true)
    public void afterClass() {
        Browser.closeDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        soft = new SoftAssert();
    }
}
