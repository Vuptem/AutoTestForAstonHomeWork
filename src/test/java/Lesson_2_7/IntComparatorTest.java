package Lesson_2_7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntComparatorTest {
    @Test
    void less() {
        assertEquals(-1, IntComparator.compare(1, 2));
    }

    @Test
    void equal() {
        assertEquals(0, IntComparator.compare(2, 2));
    }

    @Test
    void greater() {
        assertEquals(1, IntComparator.compare(3, 2));
    }
}
