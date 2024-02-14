package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// testing for WorkoutLog class
class WorkoutLogTest {

    WorkoutLog log;
    ArrayList<GymSession> sessions;
    ArrayList<PersonalBest> pbs;
    // session 1
    GymExercise exercise1;
    GymExercise bwExercise;
    ArrayList<GymExercise> exerciseList;
    GymSession session1;

    // session 1 clone
    GymExercise exerciseClone;
    GymExercise bwExerciseClone;
    ArrayList<GymExercise> exerciseListClone;
    GymSession session1Clone;

    // session 2
    GymExercise exercise2;
    GymExercise bwExercise2;
    ArrayList<GymExercise> exerciseList2;
    GymSession session2;

    // Personal Bests
    PersonalBest pb1;
    PersonalBest pb2;

    @BeforeEach
    public void setup() {
        exercise1 = new GymExercise("Dumbell Curls", 20, 0, 4, 12);
        bwExercise = new GymExercise("Push ups", 0, 80, 4, 30);
        exerciseList = new ArrayList<GymExercise>();
        exerciseList.add(exercise1);
        exerciseList.add(bwExercise);
        session1 = new GymSession(exerciseList, "Feb 3, 2024"); // 10560 lbs

        exerciseClone = new GymExercise("Dumbell Curls", 20, 0, 4, 12);
        bwExerciseClone = new GymExercise("Push ups", 0, 80, 4, 30);
        exerciseListClone = new ArrayList<GymExercise>();
        exerciseListClone.add(exerciseClone);
        exerciseListClone.add(bwExerciseClone);
        session1Clone = new GymSession(exerciseListClone, "Feb 3, 2024"); // 10560 lbs

        exercise2 = new GymExercise("Barbell row", 115, 0, 4, 10);
        bwExercise2 = new GymExercise("Push ups", 0, 80, 4, 30);
        exerciseList2 = new ArrayList<GymExercise>();
        exerciseList2.add(exercise2);
        exerciseList2.add(bwExercise2);
        session2 = new GymSession(exerciseList2, "Feb 3, 2024"); // 14200 lbs

        pb1 = new PersonalBest("Deadlift", "Jan 20, 2024", 225);
        pb2 = new PersonalBest("Squat", "Jan 20, 2024", 255);

        sessions = new ArrayList<GymSession>();
        pbs = new ArrayList<PersonalBest>();
        log = new WorkoutLog(pbs, sessions);

    }

    @Test
    public void testAmountOfGymSessions() {
        log.addGymSession(session1);
        assertEquals(1, log.amountOfGymSessions());
        log.addGymSession(session2);
        assertEquals(2, log.amountOfGymSessions());
    }

    @Test
    // 1. different sessions
    public void testMostWeightLifted() {
        log.addGymSession(session1);
        log.addGymSession(session2);
        String answer = "Most Weight Lifted\n" + "Date: " + session2.getDate() + "\n" + "Weight: " + session2.totalWeightLifted();
        assertEquals(answer ,log.mostWeightLifted());
    }

    @Test
    // 2. duplicate
    public void testSameWeightMostWeightLifted() {
        log.addGymSession(session1);
        log.addGymSession(session1Clone);
        String answer = "Most Weight Lifted\n" + "Date: " + session1.getDate() + "\n" + "Weight: " + session1.totalWeightLifted();
        assertEquals(answer ,log.mostWeightLifted());
    }

    @Test
    public void testAllPersonalBests() {
        log.addPersonalBest(pb1);
        log.addPersonalBest(pb2);
        String answer = "Personal Best: " + pb1.getName() + " - " + pb1.getWeight() + "lbs on " + pb1.getDate()
                + "\n" + "Personal Best: " + pb2.getName() + " - " + pb2.getWeight() + "lbs on " + pb2.getDate() + "\n";
        assertEquals(answer, log.allPersonalBests());
    }




    @Test
    public void testAllGymSessions() {
        log.addGymSession(session1);
        log.addGymSession(session2);
        assertEquals(session1.toString() + "\n" + session2.toString() + "\n", log.allGymSessions());
    }



    @Test
    public void testAddPersonalBest() {
        log.addPersonalBest(pb1);
        assertEquals(pb1.toString() + "\n",log.allPersonalBests());
    }

    @Test
    public void testAddGymSession() {
        log.addGymSession(session1);
        assertEquals(session1.toString() + "\n", log.allGymSessions());
    }
















}