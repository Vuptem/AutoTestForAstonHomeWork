package Lesson_2_7;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IntComparatorTest {
    @Test
    public void less() {
        assertEquals(IntComparator.compare(1, 2), -1);
    }

    @Test
    public void equal() {
        assertEquals(IntComparator.compare(2, 2), 0);
    }

    @Test
    public void greater() {
        assertEquals(IntComparator.compare(3, 2), 1);
    }
}
