package Lesson_2_4;

public class Triangle implements Shape2D {
    private final double a, b, c;
    private final String fillColor, borderColor;

    public Triangle(double a, double b, double c,
                    String fillColor, String borderColor) {
        if (a <= 0 || b <= 0 || c <= 0) throw new IllegalArgumentException("Стороны должны быть > 0");
        if (!(a + b > c && a + c > b && b + c > a))
            throw new IllegalArgumentException("Нарушено неравенство треугольника");
        this.a = a;
        this.b = b;
        this.c = c;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public double area() {
        double p2 = perimeter() / 2.0;
        return Math.sqrt(p2 * (p2 - a) * (p2 - b) * (p2 - c));
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}
