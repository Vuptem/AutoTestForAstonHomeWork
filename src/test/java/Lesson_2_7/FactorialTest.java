package Lesson_2_7;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class FactorialTest {

    @DataProvider
    public Object[][] okData() {
        return new Object[][]{
                {0, 1L},
                {1, 1L},
                {2, 2L},
                {3, 6L},
                {5, 120L}
        };
    }

    @Test(dataProvider = "okData")
    public void ok(int n, long expected) {
        assertEquals(Factorial.of(n), expected);
    }

    @Test
    public void negativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.of(-1));
    }
}
