package com.cookieClickerBot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Long.parseLong;

public class HomePage {
    private final WebDriver driver;

    @FindBy(how = How.ID, using = "cookies")
    private WebElement cookieCountElement;

    @FindBy(how = How.ID, using = "bigCookie")
    private WebElement cookieElement;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean isPageOpened() {
        return cookieCountElement.getText().contains("cookies");
    }

    public long getCurrentCookieCount() {
        String cookieCountElementText = cookieCountElement.getText();
        return parseLong(cookieCountElementText.split(" ")[0]);
    }

    public void clickCookie() {
        cookieElement.click();
    }
}
