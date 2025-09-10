package Lesson_2_6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Иванов", "A-01", 1, Arrays.asList(5, 4, 3, 5)));
        students.add(new Student("Петров", "A-01", 1, Arrays.asList(2, 2, 3)));
        students.add(new Student("Сидоров", "B-22", 2, Arrays.asList(3, 3, 4)));
        students.add(new Student("Кузнецов", "B-22", 2, Arrays.asList(5, 5, 5)));
        students.add(new Student("Смирнов", "C-03", 3, Arrays.asList(2, 4, 2)));

        System.out.println("Изначально:");
        students.forEach(System.out::println);

        StudentsService.removeLowAverage(students);
        System.out.println("\nПосле удаления <3:");
        students.forEach(System.out::println);

        StudentsService.promoteEligible(students);
        System.out.println("\nПосле перевода на след. курс (avg>=3):");
        students.forEach(System.out::println);

        System.out.println();
        StudentsService.printStudents(students, 2);
        StudentsService.printStudents(students, 3);
        StudentsService.printStudents(students, 4);

        PhoneBook pb = new PhoneBook();
        pb.add("Иванов", "8-999-111-22-33");
        pb.add("Петров", "8-999-222-33-44");
        pb.add("Иванов", "8-999-333-44-55");
        pb.add("Сидоренко", "8-000-000-00-00");

        System.out.println("\nТелефонный справочник:");
        System.out.println("Иванов → " + pb.get("Иванов"));
        System.out.println("Петров → " + pb.get("Петров"));
        System.out.println("Неизвестный → " + pb.get("Нет Такого"));
    }
}
