package Lesson_2_7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntCalcTest {
    @Test
    void add_ok() {
        assertEquals(7, IntCalc.add(3, 4));
    }

    @Test
    void sub_ok() {
        assertEquals(-1, IntCalc.sub(3, 4));
    }

    @Test
    void mul_ok() {
        assertEquals(12, IntCalc.mul(3, 4));
    }

    @Test
    void div_ok() {
        assertEquals(2, IntCalc.div(8, 4));
    }

    @Test
    void div_byZero() {
        assertThrows(ArithmeticException.class,
                () -> IntCalc.div(1, 0));
    }
}
