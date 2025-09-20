package Lesson_2_4;

public class Dog extends Animal {
    private static int dogsCount = 0;
    private final int maxRun = 500;
    private final int maxSwim = 10;

    public Dog(String name) {
        super(name);
        dogsCount++;
    }

    @Override
    public boolean run(int distance) {
        boolean ok = distance >= 0 && distance <= maxRun;
        System.out.printf("%s: бег %d m = %s%n", getName(), distance, ok ? "Успех" : "Не удалось");
        return ok;
    }

    @Override
    public boolean swim(int distance) {
        boolean ok = distance >= 0 && distance <= maxSwim;
        System.out.printf("%s: плавание %d m = %s%n", getName(), distance, ok ? "Успех" : "Не удалось");
        return ok;
    }

    public static int getDogsCount() {
        return dogsCount;
    }
}
