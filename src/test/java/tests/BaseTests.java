package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTests {

    @BeforeAll
    static void openMainPage() {

        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browser_version", "100.0");
        String browserSize = System.getProperty("browser_size", "1920x1080");
        String remote = System.getProperty("remote", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = "https://spoonacular.com";
        Configuration.remote = remote;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Selenide.closeWebDriver();
    }
}