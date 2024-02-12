package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymSessionTest {

    GymExercise exercise1;
    GymExercise exercise2;
    GymExercise bwExercise;
    ArrayList<GymExercise> exerciseList;
    GymSession session;

    @BeforeEach
    public void setup() {
        exercise1 = new GymExercise("Dumbell Curls", 20, 0, 4, 12);
        exercise2 = new GymExercise("Lat Pullover", 50, 0, 3, 13);
        bwExercise = new GymExercise("Push ups", 0, 80, 4, 30);
        exerciseList = new ArrayList<GymExercise>();
        exerciseList.add(exercise1);
        exerciseList.add(bwExercise);
        session = new GymSession(exerciseList, "Feb 3, 2024");
    }

    @Test
    public void testTotalWeightLifted() {
        assertEquals(((20*4*12) + (80*4*30)), session.totalWeightLifted());
    }

    // *start here* add tests for addExercise single and multiple



}
