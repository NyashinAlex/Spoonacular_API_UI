package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTests {

    @BeforeAll
    static void configBrowser() {

        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browser_version", "103.0");
        String browserSize = System.getProperty("browser_size", "1920x1080");
        String remote = System.getProperty("remote");

        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = "https://spoonacular.com";

        if (remote != null) {
            Configuration.remote = remote;
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Selenide.closeWebDriver();
    }
}