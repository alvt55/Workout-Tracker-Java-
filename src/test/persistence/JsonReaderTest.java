package persistence;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.GymExercise;
import model.GymSession;
import model.WorkoutLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: some tests based on JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutLog wl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkoutLog.json");
        try {
            WorkoutLog wl = reader.read();
            assertEquals(0, wl.amountOfPersonalBests());
            assertEquals(0, wl.amountOfGymSessions());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkoutLog.json");
        try {
            WorkoutLog wl = reader.read();
            assertEquals(2, wl.amountOfPersonalBests());
            assertEquals(2, wl.amountOfGymSessions());
            assertEquals("Most Weight Lifted\n" + "Date: " + "march 1" + "\n" + "Weight: " + "5865", wl.mostWeightLifted());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

