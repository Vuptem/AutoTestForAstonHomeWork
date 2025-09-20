package Lesson_2_4;

public class Rectangle implements Shape2D {
    private final double a, b;
    private final String fillColor, borderColor;

    public Rectangle(double a, double b, String fillColor, String borderColor) {
        if (a <= 0 || b <= 0) throw new IllegalArgumentException("Стороны должны быть > 0");
        this.a = a;
        this.b = b;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double perimeter() {
        return 2 * (a + b);
    }

    @Override
    public double area() {
        return a * b;
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
