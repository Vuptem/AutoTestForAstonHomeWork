package Lesson_2_4;

public class Circle implements Shape2D {
    private final double r;
    private final String fillColor;
    private final String borderColor;

    public Circle(double r, String fillColor, String borderColor) {
        if (r <= 0) throw new IllegalArgumentException("Радиус должен быть > 0");
        this.r = r;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public double area() {
        return Math.PI * r * r;
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
