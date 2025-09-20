package Lesson_2_4;

public interface Shape2D {
    double perimeter();

    double area();

    String getFillColor();

    String getBorderColor();

    default String info() {
        return String.format("P=%.2f, S=%.2f, fill=%s, border=%s",
                perimeter(),
                area(),
                getFillColor(),
                getBorderColor());
    }
}
