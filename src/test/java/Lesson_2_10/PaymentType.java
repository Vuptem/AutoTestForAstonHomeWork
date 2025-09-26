package Lesson_2_10;

public enum PaymentType {
    CONNECTION("Услуги связи", "pay-connection"),
    INTERNET("Домашний интернет", "pay-internet"),
    INSTALMENT("Рассрочка", "pay-instalment"),
    ARREARS("Задолженность", "pay-arrears");

    public final String label;
    public final String formId;

    PaymentType(String label, String formId) {
        this.label = label;
        this.formId = formId;
    }
}
