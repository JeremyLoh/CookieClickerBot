package com.cookieClickerBot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class OptionPage {
    private final WebDriver driver;

    @FindBy(how = How.CSS, using = "#menu .close")
    private WebElement closeMenuButton;

    @FindBy(how = How.LINK_TEXT, using = "Export save")
    private WebElement exportSaveToText;

    public OptionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        waitForElement(closeMenuButton);
        waitForElement(exportSaveToText);
    }

    private void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(visibilityOf(element));
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(elementToBeClickable(element));
    }

    public String exportTextSave() {
        exportSaveToText.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement saveTextArea = wait.until((driver) -> driver.findElement(By.cssSelector("#promptContent textarea")));
        return saveTextArea.getText();
    }

    public void closeOptionsMenu() {
        closeMenuButton.click();
    }
}
