package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymPersonalBestTest {

    PersonalBest pb;

    @BeforeEach
    public void setup() {
        pb = new PersonalBest("Deadlift", "Jan 20, 2024", 225);
    }

    @Test
    public void testToString() {
        assertEquals("Personal Best: Deadlift - 225lbs on Jan 20, 2024",pb.toString());
    }
}
