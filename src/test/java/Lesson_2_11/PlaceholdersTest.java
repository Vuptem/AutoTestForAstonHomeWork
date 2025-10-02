package Lesson_2_11;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PlaceholdersTest extends BaseTest {

    @Test
    void placeholdersAllPaymentTypes() {
        MtsHomePage page = new MtsHomePage(driver);
        page.open();
        acceptCookiesIfShown();
        TopUpBlock block = page.topUpBlock();

        List<String> order = List.of("Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность", "Услуги связи");

        for (String item : order) {
            block.selectPaymentType(item);

            String formId = switch (item) {
                case "Услуги связи" -> "pay-connection";
                case "Домашний интернет" -> "pay-internet";
                case "Рассрочка" -> "pay-instalment";
                case "Задолженность" -> "pay-arrears";
                default -> throw new IllegalStateException();
            };
            String clazz = driver.findElement(By.id(formId)).getAttribute("class");
            assertThat(clazz).contains("pay-form").contains("opened");
        }
    }
}