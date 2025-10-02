package Lesson_2_11;

import Lesson_2_11.BaseTest;
import Lesson_2_11.ConfirmModal;
import Lesson_2_11.MtsHomePage;
import Lesson_2_11.TopUpBlock;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("MTS.BY")
@Feature("Онлайн пополнение без комиссии")
@Story("Услуги связи — позитивный поток до модалки оплаты")
@Owner("QA Student")
@Severity(SeverityLevel.CRITICAL)
@ExtendWith(AllureWatcher.class)
public class ServicesFlowAllureTest extends BaseTest {

    @Test
    @DisplayName("Успешный переход в модалку оплаты для 'Услуги связи' и проверки внутри")
    void servicesFlow_allure() {
        Allure.step("Открыть главную mts.by", () -> {
            MtsHomePage page = new MtsHomePage(driver);
            page.open();
            acceptCookiesIfShown();

            Allure.step("Скролл до блока и выбор варианта 'Услуги связи'", () -> {
                TopUpBlock block = page.topUpBlock();
                block.selectPaymentType("Услуги связи");

                Allure.step("Ввод номера телефона 2977777777", () -> block.typePhone("2977777777"));
                Allure.step("Ввод суммы 1 BYN", () -> block.typeAmount("1"));
                Allure.step("Ввод email test@test.by", () -> block.typeEmail("test@test.by"));

                ConfirmModal modal = Allure.step("Нажать 'Продолжить' и дождаться модального окна оплаты",
                        () -> block.clickContinueExpectModal());

                Allure.step("Проверить сумму, детали и пустые поля карты в модальном окне", () -> {
                    assertThat(modal.getAmountText()).isEqualTo("1.00 BYN");
                    assertThat(modal.getDetailsText())
                            .contains("Оплата: Услуги связи")
                            .contains("Номер:375297777777");
                    assertThat(modal.isCardNumberEmpty()).isTrue();
                    assertThat(modal.isExpiryEmpty()).isTrue();
                    assertThat(modal.isCvcEmpty()).isTrue();
                    assertThat(modal.getPayButtonText()).contains("Оплатить 1.00 BYN");
                });

                modal.leave();
            });
        });
    }
}
