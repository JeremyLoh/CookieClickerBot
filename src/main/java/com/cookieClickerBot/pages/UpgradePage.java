package com.cookieClickerBot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class UpgradePage {
    private static WebDriver driver;

    @FindBy(how = How.CSS, using = "#products .product.enabled")
    private List<WebElement> enabledUpgrades;

    public UpgradePage(WebDriver driver) {
        UpgradePage.driver = driver;
        PageFactory.initElements(UpgradePage.driver, this);
    }

    private static void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(elementToBeClickable(element));
    }

    public void clickMostExpensiveUpgrade(long availableCookies) {
        enabledUpgrades.forEach(UpgradePage::waitForElement);
        enabledUpgrades.stream()
                .map(UpgradePage::getContent)
                .filter((WebElement upgrade) -> availableCookies >= getPrice(upgrade))
                .sorted(Comparator.comparingLong(UpgradePage::getPrice).reversed())
                .limit(1)
                .forEach((WebElement upgrade) -> ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click()", upgrade));
    }

    private static WebElement getContent(WebElement upgrade) {
        waitForElement(upgrade);
        return upgrade.findElement(By.className("content"));
    }

    private static Long getPrice(WebElement upgrade) {
        return Long.parseLong(upgrade.findElement(By.className("price"))
                .getText()
                .replace(",", ""));
    }
}
