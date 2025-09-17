package Lesson_2_7;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertThrows;

public class IntCalcTest {
    @Test
    public void add_ok() {
        assertEquals(IntCalc.add(3, 4), 7);
    }

    @Test
    public void sub_ok() {
        assertEquals(IntCalc.sub(3, 4), -1);
    }

    @Test
    public void mul_ok() {
        assertEquals(IntCalc.mul(3, 4), 12);
    }

    @Test
    public void div_ok() {
        assertEquals(IntCalc.div(8, 4), 2);
    }

    @Test
    public void div_byZero() {
        assertThrows(ArithmeticException.class, () -> IntCalc.div(1, 0));
    }
}
