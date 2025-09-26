package Lesson_2_10;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServicesFlowTest extends BaseTest {

    @Test
    void servicesFlow() {
        MtsHomePage page = new MtsHomePage(driver);
        page.open();
        acceptCookiesIfShown();

        TopUpBlock block = page.topUpBlock();
        block.selectPaymentType("Услуги связи");
        block.typePhone("2977777777");
        block.typeAmount("1");
        block.typeEmail("test@test.by");

        ConfirmModal modal = block.clickContinueExpectModal();


        assertThat(modal.getAmountText()).isEqualTo("1.00 BYN");
        assertThat(modal.getDetailsText()).contains("Оплата: Услуги связи").contains("Номер:375297777777");
        assertThat(modal.isCardNumberEmpty()).isTrue();
        assertThat(modal.isExpiryEmpty()).isTrue();
        assertThat(modal.isCvcEmpty()).isTrue();
        assertThat(modal.getPayButtonText()).contains("Оплатить 1.00 BYN");

        modal.leave();
    }
}
