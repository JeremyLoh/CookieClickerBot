package com.cookieClickerBot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HomePage {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "cookies")
    private WebElement cookieCountElement;

    @FindBy(how = How.ID, using = "bigCookie")
    private WebElement cookieElement;

    @FindBy(how = How.CSS, using = "#prefsButton .subButton")
    private WebElement optionsMenuButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        waitForElement(cookieElement);
        waitForElement(cookieCountElement);
        waitForElement(optionsMenuButton);
    }

    private void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(visibilityOf(element));
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(elementToBeClickable(element));
    }

    public long getCurrentCookieCount() {
        String cookieCountElementText = cookieCountElement.getText();
        return parseLong(cookieCountElementText.split(" ")[0]);
    }

    public double getCookiesPerSecond() {
        String cookieCountElementText = cookieCountElement.getText();
        return parseDouble(cookieCountElementText.split("per second: ")[1]);
    }

    public void clickCookie() {
        cookieElement.click();
    }

    public void openOptionsMenu() {
        optionsMenuButton.click();
    }
}
