package persistence;


import model.GymExercise;
import model.GymSession;
import model.PersonalBest;

import model.WorkoutLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: some tests based on JsonSerializationDemo
class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            ArrayList<PersonalBest> pbs = new ArrayList<PersonalBest>();
            ArrayList<GymSession> sessions = new ArrayList<GymSession>();
            WorkoutLog wr = new WorkoutLog(pbs, sessions);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ArrayList<PersonalBest> pbs = new ArrayList<PersonalBest>();
            ArrayList<GymSession> sessions = new ArrayList<GymSession>();
            WorkoutLog wr = new WorkoutLog(pbs, sessions);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkoutLog.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutLog.json");
            wr = reader.read();
            assertEquals(0, wr.amountOfPersonalBests());
            assertEquals(0, wr.amountOfGymSessions());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ArrayList<PersonalBest> pbs = new ArrayList<PersonalBest>();
            ArrayList<GymSession> sessions = new ArrayList<GymSession>();
            ArrayList<GymExercise> exercises1 = new ArrayList<GymExercise>();
            ArrayList<GymExercise> exercises2 = new ArrayList<GymExercise>();

            GymExercise one = new GymExercise("pull ups", 155, 0, 3, 10);
            GymExercise two = new GymExercise("lat pullover", 45, 0, 3, 9);
            GymExercise three = new GymExercise("chest press ", 10045, 0, 4, 12);
            exercises1.add(one);
            exercises1.add(two);
            exercises2.add(three);

            WorkoutLog wr = new WorkoutLog(pbs, sessions);
            wr.addGymSession(new GymSession(exercises1, "march 1"));
            wr.addGymSession(new GymSession(exercises2, "march 2"));
            wr.addPersonalBest(new PersonalBest("bench ", "jan 25", 135));
            wr.addPersonalBest(new PersonalBest("squat ", "feb 5", 205));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkoutLog.json");
            writer.open();
            writer.write(wr);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkoutLog.json");
            wr = reader.read();

            assertEquals(2, wr.amountOfPersonalBests());
            assertEquals(2, wr.amountOfGymSessions());
            assertEquals("Most Weight Lifted\n" + "Date: " + "march 2" + "\n" + "Weight: " + "482160", wr.mostWeightLifted());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
