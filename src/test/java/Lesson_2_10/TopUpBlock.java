package Lesson_2_10;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopUpBlock {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nativeSelect = By.id("pay");
    private final By openedForm = By.cssSelector(".pay__forms .pay-form.opened");
    private final By phoneInput = By.cssSelector(".pay__forms .pay-form.opened input.phone");
    private final By amountInput = By.cssSelector(".pay__forms .pay-form.opened input.total_rub");
    private final By emailInput = By.cssSelector(".pay__forms .pay-form.opened input.email");
    private final By continueBtn = By.cssSelector(".pay__forms .pay-form.opened button[type='submit']");

    public TopUpBlock(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void selectPaymentType(String visibleText) {
        WebElement sel = driver.findElement(nativeSelect);
        ((JavascriptExecutor) driver).executeScript(
                "const sel=arguments[0], txt=arguments[1].trim();" +
                        "const opt=[...sel.options].find(o=>o.text.trim()===txt);" +
                        "if(!opt) throw new Error('option not found');" +
                        "sel.value=opt.value; sel.dispatchEvent(new Event('change',{bubbles:true}));",
                sel, visibleText
        );

        String openId = switch (visibleText) {
            case "Услуги связи" -> "pay-connection";
            case "Домашний интернет" -> "pay-internet";
            case "Рассрочка" -> "pay-instalment";
            case "Задолженность" -> "pay-arrears";
            default -> throw new IllegalArgumentException("Неизвестный пункт: " + visibleText);
        };
        wait.until(ExpectedConditions.attributeContains(By.id(openId), "class", "opened"));
    }

    public void typePhone(String phone) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        el.click();
        el.sendKeys(Keys.HOME);
        el.sendKeys(phone);
    }

    public void typeAmount(String amount) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput));
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        el.sendKeys(amount);
    }

    public void typeEmail(String email) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        el.sendKeys(email);
    }

    public ConfirmModal clickContinueExpectModal() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        new WebDriverWait(driver, java.time.Duration.ofSeconds(3))
                .until(d -> true);

        ConfirmModal modal = new ConfirmModal(driver, wait);
        modal.waitOpened();
        return modal;
    }
}
