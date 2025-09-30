package Lesson_2_7;

public class TriangleArea {
    public static double byBaseHeight(double base, double height) {
        if (base <= 0 || height <= 0)
            throw new IllegalArgumentException("Основание и Высота должны быть > 0 ");
        return 0.5 * base * height;
    }

    public static double bySides(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException("Стороны должны быть > 0");
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException("Неравенство треугольника - нарушено");
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
