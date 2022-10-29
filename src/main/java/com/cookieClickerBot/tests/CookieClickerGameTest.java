package com.cookieClickerBot.tests;

import com.cookieClickerBot.pages.HomePage;
import com.cookieClickerBot.pages.LanguageSelectHomePage;
import com.cookieClickerBot.pages.UpgradePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

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
        setupGame();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    private static void setupGame() {
        LanguageSelectHomePage languageSelectHomePage = new LanguageSelectHomePage(driver);
        languageSelectHomePage.setGameLanguage();
    }

    @Test
    void clickCookieOnce() {
        HomePage homePage = new HomePage(driver);
        assertEquals(0, homePage.getCurrentCookieCount());
        homePage.clickCookie();
        assertEquals(1, homePage.getCurrentCookieCount());
    }

    @Test
    void buyFirstUpgrade() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        HomePage homePage = new HomePage(driver);
        UpgradePage upgradePage = new UpgradePage(driver);
        assertEquals(0, homePage.getCurrentCookieCount());
        for (int i = 0; i < 15; i++) {
            homePage.clickCookie();
        }
        assertEquals(0, homePage.getCookiesPerSecond());
        wait.until((driver) -> homePage.getCurrentCookieCount() == 15);
        upgradePage.clickMostExpensiveUpgrade(15);
        wait.until((driver) -> homePage.getCookiesPerSecond() > 0);
    }
}
