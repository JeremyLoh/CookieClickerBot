package com.cookieClickerBot.tests;

import com.cookieClickerBot.pages.HomePage;
import com.cookieClickerBot.pages.LanguageSelectHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CookieClickerGameTest {
    private static WebDriver driver;
    private static final String PAGE_URL = "https://orteil.dashnet.org/cookieclicker/";

    @BeforeAll
    static void setupBeforeAll() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    @Test
    void loadGameHomePage() {
        LanguageSelectHomePage languageSelectHomePage = new LanguageSelectHomePage(driver);
        languageSelectHomePage.setGameLanguage();
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isPageOpened());
    }
}
