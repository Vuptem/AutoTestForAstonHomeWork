package Lesson_2_4;

public class MainShape2d {
    public static void main(String[] args) {
        Shape2D[] shapes = new Shape2D[]{
                new Circle(10, "Yellow", "Black"),
                new Rectangle(5, 8, "Green", "Gray"),
                new Triangle(3, 4, 5, "Blue", "Red")
        };

        for (Shape2D s : shapes) {
            System.out.println(s.getClass().getSimpleName() + " => " + s.info());
        }
    }
}
