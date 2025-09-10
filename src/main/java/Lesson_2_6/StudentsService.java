package Lesson_2_6;

import java.util.Iterator;
import java.util.Set;

public class StudentsService {
    public static void removeLowAverage(Set<Student> students) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.average() < 3.0) it.remove();
        }
    }

    public static void promoteEligible(Set<Student> students) {
        for (Student s : students) {
            if (s.average() >= 3.0) s.promote();
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты на " + course + "м курсе");
        for (Student s : students) {
            if (s.getCourse() == course) System.out.println(" - " + s.getName());
        }
    }
}
