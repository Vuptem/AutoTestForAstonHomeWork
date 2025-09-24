package Lesson_2_6;

import java.util.*;

public class PhoneBook {
    private final Map<String, Set<String>> storage = new HashMap<>();

    public void add(String lastName, String phone) {
        storage.computeIfAbsent(lastName, k -> new LinkedHashSet<>()).add(phone);
    }

    public Set<String> get(String lastName) {
        return storage.getOrDefault(lastName, Collections.emptySet());
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}
