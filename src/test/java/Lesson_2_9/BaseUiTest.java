package Lesson_2_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseUiTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final Duration UI_TIMEOUT = Duration.ofSeconds(15);

    @BeforeEach
    void setupDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, UI_TIMEOUT);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    protected WebElement scrollToVisible(By locator) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        return el;
    }

    protected void scrollIntoCenter(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    protected void clearAndType(WebElement el, String text) {
        scrollIntoCenter(el);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);
        el.sendKeys(text);
    }

    protected void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void selectFromCustomDropdown(WebElement dropdownRoot, String optionText) {
        WebElement header = dropdownRoot.findElement(By.xpath(".//*[contains(@class,'select__current') or contains(@class,'select__header')]"));
        scrollIntoCenter(header);
        header.click();

        By option = By.xpath("//p[contains(@class,'select__option') and normalize-space()='" + optionText + "']");
        WebElement opt = wait.until(ExpectedConditions.visibilityOfElementLocated(option));
        jsClick(opt);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(option));
    }

    protected void acceptCookiesIfShown() {
        try {
            By onetrustBtn = By.id("onetrust-accept-btn-handler");
            if (!driver.findElements(onetrustBtn).isEmpty()) {
                WebElement accept = driver.findElement(onetrustBtn);
                scrollIntoCenter(accept);
                jsClick(accept);
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.invisibilityOfElementLocated(By.id("onetrust-banner-sdk")));
                return;
            }
            if (!driver.findElements(By.cssSelector(".cookie.show, .cookie")).isEmpty()) {
                By anyAccept = By.xpath("//*[contains(@class,'cookie')]//button[normalize-space()='OK' or normalize-space()='ОК' or contains(.,'Принять') or contains(.,'Соглас')]");
                var btns = driver.findElements(anyAccept);
                if (!btns.isEmpty()) {
                    WebElement btn = btns.get(0);
                    scrollIntoCenter(btn);
                    jsClick(btn);
                } else {
                    ((JavascriptExecutor) driver).executeScript(
                            "var b=document.querySelector('.cookie.show')||document.querySelector('.cookie'); if(b){b.style.display='none';}"
                    );
                }
            }
        } catch (Exception ignore) {}
    }

    protected static String norm(String s) {
        return s == null ? "" : s.replace('\u00A0',' ').replaceAll("\\s+", " ").trim();
    }

    protected WebElement blockRootByTitle(String title) {
        WebElement h2 = scrollToVisible(By.xpath("//h2[normalize-space()='" + title + "']"));
        var roots = h2.findElements(By.xpath("./ancestor::section | ./ancestor::div"));
        return roots.isEmpty() ? h2 : roots.get(0);
    }

    protected boolean waitAny(BooleanSupplier... conds) {
        long end = System.currentTimeMillis() + UI_TIMEOUT.toMillis();
        while (System.currentTimeMillis() < end) {
            for (BooleanSupplier c : conds) {
                try {
                    if (c.getAsBoolean()) return true;
                } catch (Exception ignore) {}
            }
            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }
        return false;
    }

    protected boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected void assertProceeded() {
        boolean proceeded = waitAny(
                () -> isPresent(By.xpath("//h3[contains(.,'Проверьте реквизиты') or contains(.,'Проверьте данные')]")),
                () -> driver.getCurrentUrl().toLowerCase().contains("pay")
                        || driver.getCurrentUrl().toLowerCase().contains("payments"),
                () -> isPresent(By.xpath("//*[contains(@class,'error') or contains(@class,'invalid') or contains(.,'Невер')]"))
        );
        assertTrue(proceeded, "Не дождались перехода/подтверждения/валидации после клика «Продолжить»");
    }
}
