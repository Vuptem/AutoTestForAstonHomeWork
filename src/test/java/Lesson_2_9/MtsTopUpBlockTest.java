package Lesson_2_9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTopUpBlockTest extends BaseUiTest {

    private static final String TITLE = "Онлайн пополнение без комиссии";

    @Test
    @DisplayName("Заголовок блока корректен")
    void titleIsVisible() {
        driver.get("https://www.mts.by/");
        acceptCookiesIfShown();

        WebElement h2 = scrollToVisible(By.xpath("//h2[normalize-space()='" + TITLE + "']"));
        assertEquals(TITLE, norm(h2.getText()));
    }

    @Test
    @DisplayName("Логотипы платёжных систем видны (Visa/Verified by Visa, Mastercard, Белкарт)")
    void paymentLogosVisible() {
        driver.get("https://www.mts.by/");
        acceptCookiesIfShown();

        WebElement root = blockRootByTitle(TITLE);

        assertTrue(
                checkLogoWithRetry(root,
                        new String[]{"Verified by Visa", "Visa", "VERIFIED BY VISA"},
                        "Visa/Verified by Visa"),
                "Не найден логотип: Visa/Verified by Visa"
        );

        assertTrue(
                checkLogoWithRetry(root,
                        new String[]{"Mastercard", "MasterCard", "MASTERCARD"},
                        "Mastercard"),
                "Не найден логотип: Mastercard"
        );

        assertTrue(
                checkLogoWithRetry(root,
                        new String[]{"Белкарт", "BELKART"},
                        "Белкарт"),
                "Не найден логотип: Белкарт"
        );
    }

    @Test
    @DisplayName("Ссылка «Подробнее о сервисе» работает")
    void moreLinkWorks() {
        driver.get("https://www.mts.by/");
        acceptCookiesIfShown();

        WebElement root = blockRootByTitle(TITLE);
        WebElement more = root.findElement(By.xpath(
                ".//a[contains(.,'Подробнее о сервисе') or .//span[contains(.,'Подробнее о сервисе')]]"
        ));

        String original = driver.getWindowHandle();

        try {
            scrollIntoCenter(more);
            more.click();
        } catch (ElementClickInterceptedException e) {
            acceptCookiesIfShown();
            jsClick(more);
        }

        for (String h : driver.getWindowHandles()) {
            if (!h.equals(original)) {
                driver.switchTo().window(h);
                break;
            }
        }

        String title = driver.getTitle();
        assertTrue(
                norm(title).toLowerCase().contains("онлайн пополнение")
                        || driver.getCurrentUrl().toLowerCase().contains("help")
                        || driver.getCurrentUrl().toLowerCase().contains("oplata")
                        || driver.getCurrentUrl().toLowerCase().contains("payments"),
                "Не похоже, что открылась страница с описанием сервиса"
        );
    }

    @Test
    @DisplayName("Флоу пополнения (Услуги связи, 297777777) — «Продолжить» ведёт дальше")
    void topUpFlowWork() {
        driver.get("https://www.mts.by/");
        acceptCookiesIfShown();

        WebElement root = blockRootByTitle(TITLE);

        WebElement dropdown = root.findElement(By.xpath(
                ".//*[contains(@class,'select') and .//*[contains(@class,'select__current') or contains(@class,'select__header')]]"
        ));
        selectFromCustomDropdown(dropdown, "Услуги связи");

        WebElement phone = root.findElement(By.xpath(
                ".//input[@id='connection-phone' or @name='connection-phone' or @type='tel' " +
                        "or contains(translate(@placeholder,'АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ','абвгдеёжзийклмнопрстуфхцчшщъыьэюя'),'номер')]"
        ));
        clearAndType(phone, "297777777");

        WebElement amount = root.findElement(By.xpath(
                ".//input[@id='connection-sum' or @name='connection-sum' or @type='number' " +
                        "or contains(@placeholder,'BYN') or contains(.,'BYN')]"
        ));
        clearAndType(amount, "1");

        WebElement cont = root.findElement(By.xpath(
                ".//button[.//span[normalize-space()='Продолжить'] or normalize-space()='Продолжить']"
        ));
        scrollIntoCenter(cont);
        try {
            cont.click();
        } catch (ElementClickInterceptedException e) {
            acceptCookiesIfShown();
            jsClick(cont);
        }

        assertProceeded();
    }


    private boolean checkLogoWithRetry(WebElement root, String[] variants, String humanName) {
        if (hasAnyLogoVariant(root, variants)) return true;

        acceptCookiesIfShown();
        scrollIntoCenter(root);

        boolean ok = hasAnyLogoVariant(root, variants);
        if (!ok) {
            System.out.println("Логотип не найден: " + humanName + " (variants: " + String.join(", ", variants) + ")");
        }
        return ok;
    }

    private boolean hasAnyLogoVariant(WebElement root, String[] variants) {
        for (String v : variants) {
            if (hasLogo(root, v) || hasSvgTitle(root, v)) return true;
        }
        return false;
    }

    private boolean hasLogo(WebElement root, String altContains) {
        String needle = altContains.toLowerCase();
        return !root.findElements(By.xpath(
                ".//img[contains(translate(@alt," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                        "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')," +
                        "'" + needle + "')]"
        )).isEmpty();
    }


    private boolean hasSvgTitle(WebElement root, String titleText) {
        String needle = titleText.toLowerCase();

        boolean byTitle = !root.findElements(By.xpath(
                ".//*[local-name()='svg']//*[local-name()='title' " +
                        "and contains(translate(normalize-space(.)," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                        "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')," +
                        "'" + needle + "')]"
        )).isEmpty();

        if (byTitle) return true;

        boolean byUseHref = !root.findElements(By.xpath(
                ".//*[local-name()='svg']//*[local-name()='use' and " +
                        "contains(translate(@*[local-name()='href']," +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'," +
                        "'abcdefghijklmnopqrstuvwxyzабвгдеёжзийклмнопрстуфхцчшщъыьэюя')," +
                        "'" + needle + "')]"
        )).isEmpty();

        return byUseHref;
    }
}
