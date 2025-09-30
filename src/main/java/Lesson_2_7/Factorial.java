package Lesson_2_7;

public class Factorial {
    public static long of(int n) {
        if (n < 0) throw new IllegalArgumentException("n не должен быть >= 0");
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
