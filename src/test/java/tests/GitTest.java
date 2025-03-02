package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PullRequestPage;

public class GitTest extends AbstractTest {

    private HomePage homePage;
    private PullRequestPage pullRequestPage;
    private static final String USER = "";
    private static final String PASS = "";
    private static final String PROJECT_NAME = "";

    @BeforeClass
    public void beforeActions() {
        homePage = AbstractTest.browser.openLoginPage().login(USER, PASS);
    }

    @Test(dataProvider = "attempts")
    public void test1(int att) {
        pullRequestPage = homePage
                .editReadmeFile(USER, PROJECT_NAME)
                .setText("Text " + att)
                .clickCommitChangesButton()
                .clickCreateNewBranchButton()
                .clickProposeChangesButton()
                .clickCreatePullRequestButton()
                .merge()
                .confirmMerge()
                .deleteBranch();
        Assert.assertTrue(pullRequestPage.isRestoreBranchButtonShown(), "Restore branch button should be shown.");
    }

    @DataProvider(name = "attempts")
    public static Object[][] attempts() {

        return new Object[][]{
                {1},
                {2}
        };
    }
}
