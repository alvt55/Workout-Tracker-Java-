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
    // 1. weights exercise
    public void testWeightGetTotalWeight() {
        assertEquals((135*3*8), weightExercise.getTotalWeight());
    }

    @Test
    // 2. body weight exercise
    public void testBwGetTotalWeight() {
        assertEquals((40*150), bwExercise.getTotalWeight());
    }


}
