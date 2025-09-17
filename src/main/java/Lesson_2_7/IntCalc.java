package Lesson_2_7;

public class IntCalc {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

    public static int mul(int a, int b) {
        return a * b;
    }

    public static int div(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Деление на 0 не возможно");
        return a / b;
    }
}
