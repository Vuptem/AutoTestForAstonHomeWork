package Lesson_2_7;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleAreaTest {

    @Test
    public void byBaseHeight_ok() {
        assertEquals(TriangleArea.byBaseHeight(4, 3), 6.0, 1e-9);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void byBaseHeight_badArgs1() {
        TriangleArea.byBaseHeight(0, 3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void byBaseHeight_badArgs2() {
        TriangleArea.byBaseHeight(3, -1);
    }

    @Test
    public void bySides_ok() {
        assertEquals(TriangleArea.bySides(3, 4, 5), 6.0, 1e-9);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void bySides_invalid() {
        TriangleArea.bySides(1, 2, 10);
    }
}
