package Lesson_2_10;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MtsHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By topUpWrapper = By.cssSelector(".pay__wrapper");

    public MtsHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("https://mts.by/");
        try {
            WebElement banner = driver.findElement(By.cssSelector(".cookie.show"));
            WebElement ok = banner.findElement(By.cssSelector("#cookie-agree, .cookie__ok"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ok);
        } catch (NoSuchElementException ignored) { }
    }

    public WebElement ensureTopUpVisible() {
        WebElement root = wait.until(ExpectedConditions.visibilityOfElementLocated(topUpWrapper));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", root);
        return root;
    }

    public TopUpBlock topUpBlock() {
        ensureTopUpVisible();
        return new TopUpBlock(driver, wait);
    }
}
