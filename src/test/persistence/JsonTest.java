package persistence;



import model.GymExercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExercise(GymExercise e, String n, int w, int bw, int s, int r) {
        assertEquals(n, e.getName());
        assertEquals(w, e.getWeight());
        assertEquals(bw, e.getName());
        assertEquals(s, e.getName());
        assertEquals(r, e.getName());

    }
}