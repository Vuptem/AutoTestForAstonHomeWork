package Lesson_2_4;

public abstract class Animal {
    private static int animalsCount = 0;
    private final String name;

    protected Animal(String name) {
        this.name = name;
        animalsCount++;
    }

    public String getName() {
        return name;
    }

    public boolean run(int distance) {
        System.out.printf(" %s не умеет бегать.%n ", name);
        return false;
    }

    public boolean swim(int distance) {
        System.out.printf(" % не умеет плавать.%n ", name);
        return false;
    }

    public static int getAnimalsCount() {
        return animalsCount;
    }


}
