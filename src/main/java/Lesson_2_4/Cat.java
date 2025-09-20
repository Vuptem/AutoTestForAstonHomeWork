package Lesson_2_4;

public class Cat extends Animal {
    private static int catsCount = 0;
    private final int maxRun = 200;
    private boolean satiety = false;

    public Cat(String name) {
        super(name);
        catsCount++;
    }

    @Override
    public boolean run(int distance) {
        boolean ok = distance >= 0 && distance <= maxRun;
        System.out.printf("%s: бег %d m = %s%n", getName(), distance, ok ? "Успех" : "Не удалось");
        return ok;
    }

    @Override
    public boolean swim(int distance) {
        System.out.printf("%s: кошки не плавуют (попытка %d m).%n", getName(), distance);
        return false;
    }

    public void eatFrom(Bowl bowl, int food) {
        if (food <= 0) return;
        if (bowl.take(food)) {
            satiety = true;
            System.out.printf("%s поел %d г. Остаток в миске: %d г. %n", getName(), food, bowl.getFood());
        } else {
            System.out.printf("%s: еды недостаточно, кот не стал есть. Остаток: %d г.%n", getName(), bowl.getFood());
        }
    }

    public boolean isSatiated() {
        return satiety;
    }

    public static int getCatsCount() {
        return catsCount;
    }
}
