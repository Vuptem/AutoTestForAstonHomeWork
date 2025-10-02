package Lesson_2_11;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

public class AllureWatcher implements TestWatcher, BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        Allure.label("owner", "QA Student");
        Allure.label("layer", "ui");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = extractDriver(context.getRequiredTestInstance());
        if (driver == null) return;

        attachScreenshot(driver);
        attachPageSource(driver);
        attachBrowserLogs();
    }

    private WebDriver extractDriver(Object testInstance) {
        try {
            Field f = testInstance.getClass().getSuperclass().getDeclaredField("driver");
            f.setAccessible(true);
            return (WebDriver) f.get(testInstance);
        } catch (Exception ignored) {
            try {
                Field f = testInstance.getClass().getDeclaredField("driver");
                f.setAccessible(true);
                return (WebDriver) f.get(testInstance);
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Attachment(value = "Page Source", type = "text/html", fileExtension = ".html")
    private byte[] attachPageSource(WebDriver driver) {
        try {
            return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            return ("<html><body>Cannot get page source: " + e + "</body></html>")
                    .getBytes(StandardCharsets.UTF_8);
        }
    }

    @Attachment(value = "Browser Console", type = "text/plain")
    private String attachBrowserLogs() {
        return "No console logs collected in this setup.";
    }
}
