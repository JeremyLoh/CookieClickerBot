package com.cookieClickerBot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LanguageSelectHomePage {
    private static final String PAGE_URL = "https://orteil.dashnet.org/cookieclicker/";
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "langSelect-EN")
    private WebElement englishLanguageSelectElement;

    public LanguageSelectHomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(PAGE_URL);
        PageFactory.initElements(this.driver, this);
    }

    public void setGameLanguage() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(visibilityOf(englishLanguageSelectElement));
        englishLanguageSelectElement.click();
    }
}
