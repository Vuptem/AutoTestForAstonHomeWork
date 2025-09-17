package Lesson_2_7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {

    @ParameterizedTest
    @CsvSource({
            "0,1",
            "1,1",
            "2,2",
            "3,6",
            "5,120"
    })
    void ok(int n, long expected) {
        assertEquals(expected, Factorial.of(n));
    }

    @Test
    void negativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.of(-1));
    }

}
