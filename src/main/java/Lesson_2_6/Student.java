package Lesson_2_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Student {
    private final String name;
    private final String group;
    private int course;
    private final List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades) {
        this.name = Objects.requireNonNull(name);
        this.group = Objects.requireNonNull(group);
        if (course <= 0) throw new IllegalArgumentException("Курс должен быть >= 1");
        this.course = course;
        this.grades = new ArrayList<>(Objects.requireNonNull(grades));
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCourse() {
        return course;
    }

    public List<Integer> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    public double average() {
        if (grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int g : grades) sum += g;
        return sum * 1.0 / grades.size();
    }

    public void promote() {
        this.course++;
    }

    @Override
    public String toString() {
        return "Student{" + name + ", grp=" + group +
                ", course=" + course + ", avg="
                + String.format("%.2f", average()) + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student that = (Student) o;
        return Objects.equals(name, that.name) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group);
    }
}
