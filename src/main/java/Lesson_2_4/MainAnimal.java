package Lesson_2_4;

public class MainAnimal {
    public static void main(String[] args) {
        Animal[] animals = {
                new Dog("Бобик"),
                new Dog("Рэкс"),
                new Cat("Пумба"),
                new Cat("Каракал")
        };

        animals[0].run(300);
        animals[0].swim(7);
        animals[2].run(150);
        animals[2].swim(5);

        System.out.printf("Всего животных: %d, собака: %d, котов: %d%n",
                Animal.getAnimalsCount(),
                Dog.getAnimalsCount(),
                Cat.getCatsCount());
        Bowl bowl = new Bowl(200);
        System.out.println("Начальная " + bowl);

        Cat[] cats = {
                new Cat("Барсик"),
                new Cat("Снежок"),
                new Cat("Матроскин")};

        int[] portions = {80, 90, 80};

        for (int i = 0; i < cats.length; i++) {
            cats[i].eatFrom(bowl, portions[i]);
        }
        bowl.add(100);
        System.out.println("Пополнили: " + bowl);
        for (int i = 0; i < cats.length; i++) {
            if (!cats[i].isSatiated()) {
                cats[i].eatFrom(bowl, portions[i]);
            }
        }

        System.out.println("== Сытость котов ==");
        for (Cat c : cats) {
            System.out.printf("%s: %s%n", c.getName(), c.isSatiated() ? "сыт" : "голоден");
        }

    }
}
