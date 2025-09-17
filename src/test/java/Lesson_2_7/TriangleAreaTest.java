package Lesson_2_7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleAreaTest {
    @Test
    void byBaseHeight_ok(){
        assertEquals(6.0,TriangleArea.byBaseHeight(4,3), 1e-9);
    }

    @Test
    void byBaseHeight_badArgs(){
        assertThrows(IllegalArgumentException.class,() -> TriangleArea.byBaseHeight(0,3));
        assertThrows(IllegalArgumentException.class,()-> TriangleArea.byBaseHeight(3,-1));
    }

    @Test
    void bySides_ok(){
        assertEquals(6.0, TriangleArea.bySides(3,4,5), 1e-9);
    }

    @Test
    void bySides_invalid(){
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.bySides(1,2,10));
    }
}
