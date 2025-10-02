package Lesson_2_11;

import Lesson_2_11.BaseTest;
import Lesson_2_11.MtsHomePage;
import Lesson_2_11.TopUpBlock;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("MTS.BY")
@Feature("Онлайн пополнение без комиссии")
@Story("Переключение вариантов оплаты и проверка открытия соответствующих форм")
@Owner("QA Student")
@Severity(SeverityLevel.NORMAL)
@ExtendWith(AllureWatcher.class)
public class PlaceholdersAllureTest extends BaseTest {

    @Test
    @DisplayName("Варианты оплаты: формы открываются корректно")
    void placeholdersAllPaymentTypes_allure() {
        MtsHomePage page = new MtsHomePage(driver);
        page.open();
        acceptCookiesIfShown();

        TopUpBlock block = page.topUpBlock();

        List<String> order = List.of(
                "Услуги связи",
                "Домашний интернет",
                "Рассрочка",
                "Задолженность",
                "Услуги связи"
        );

        for (String item : order) {
            Allure.step("Выбрать пункт: " + item, () -> block.selectPaymentType(item));

            String formId = switch (item) {
                case "Услуги связи" -> "pay-connection";
                case "Домашний интернет" -> "pay-internet";
                case "Рассрочка" -> "pay-instalment";
                case "Задолженность" -> "pay-arrears";
                default -> throw new IllegalStateException("Неожиданный пункт: " + item);
            };

            Allure.step("Проверить, что форма '" + formId + "' открыта", () -> {
                String clazz = driver.findElement(By.id(formId)).getAttribute("class");
                assertThat(clazz).contains("pay-form").contains("opened");
            });
        }
    }
}
