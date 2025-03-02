package ui.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.pages.HomePage;
import ui.pages.LoginPage;

public class Browser {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String originUrl = "https://www.aveve.be/";

    public static WebDriver getDriver() {
        if (driver.get() != null) {
            return driver.get();
        }
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver.set(new ChromeDriver(options));
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static String getURL() {
        return originUrl;
    }

    public HomePage openHomePage() {
        getDriver().get(getURL());
        return new HomePage();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
