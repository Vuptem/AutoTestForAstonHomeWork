package Lesson_2_11;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmModal {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By frameBy = By.cssSelector("iframe[src*='widget_v2'], iframe[src*='bepaid'], iframe[src*='widget']");
    private final By amountBy = By.cssSelector(".pay-description__cost span, .pay-description__cost");
    private final By detailsBy = By.xpath("//*[contains(normalize-space(.),'Оплата:')]");
    private final By cardNumberBy = By.id("cc-number");
    private final By expBy = By.cssSelector("input[formcontrolname='expirationDate']");
    private final By cvcBy = By.cssSelector("input[formcontrolname='cvc']");
    private final By payBtnBy = By.cssSelector("button[type='submit']");

    public ConfirmModal(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(25));
    }

    public void waitOpened() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameBy));
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountBy));
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailsBy));
    }

    public String getAmountText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(amountBy))
                .getText().replace('\u00A0',' ').trim();
    }

    public String getDetailsText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(detailsBy))
                .getText().replace('\u00A0',' ').trim();
    }

    public boolean isCardNumberEmpty() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(cardNumberBy))
                .getAttribute("value").isEmpty();
    }

    public boolean isExpiryEmpty() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(expBy))
                .getAttribute("value").isEmpty();
    }

    public boolean isCvcEmpty() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(cvcBy))
                .getAttribute("value").isEmpty();
    }

    public String getPayButtonText() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(payBtnBy))
                .getText().replace('\u00A0',' ').trim();
    }

    public void leave() {
        driver.switchTo().defaultContent();
    }
}