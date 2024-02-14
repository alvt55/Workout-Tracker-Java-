package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymExerciseTest {

    GymExercise weightExercise;
    GymExercise bwExercise;
    @BeforeEach
    public void setup() {
        weightExercise = new GymExercise("Bench Press", 135, 0, 3, 8);
        bwExercise = new GymExercise("Pull ups", 0, 150, 4, 10);

    }

    @Test
    public void testconstructor() {
        assertEquals("Bench Press", weightExercise.getName());
        assertEquals(135, weightExercise.getWeight());
        assertEquals(0, weightExercise.getBodyWeight());
        assertEquals(3, weightExercise.getSets());
        assertEquals(8, weightExercise.getReps());

        assertEquals("Pull ups", bwExercise.getName());
        assertEquals(0, bwExercise.getWeight());
        assertEquals(150, bwExercise.getBodyWeight());
        assertEquals(4, bwExercise.getSets());
        assertEquals(10, bwExercise.getReps());


    }
    @Test
    // 1. weights exercise
    public void testWeightGetTotalWeight() {
        assertEquals((135*3*8), weightExercise.getTotalWeight());
    }

    @Test
    // 2. body weight exercise
    public void testBwGetTotalWeight() {
        assertEquals((40*150), bwExercise.getTotalWeight());
    }


    @Test
    // both toStrings
    public void testToString() {
        String answer1 = weightExercise.getSets() + "x" + weightExercise.getReps()
                + " (" + weightExercise.getWeight() + ") " + weightExercise.getName();
        assertEquals(answer1 ,weightExercise.toString());

        String answer2 = bwExercise.getSets() + "x" + bwExercise.getReps() + " (bodyweight) " +
                bwExercise.getBodyWeight();
        assertEquals(answer2, bwExercise.toString());
    }

}
