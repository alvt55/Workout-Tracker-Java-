package model;

import java.util.ArrayList;

// represents a collection of gym exercises and info about the workout
public class GymSession {
    private ArrayList<GymExercise> listOfGymExercise;
    private String date;


    // MODIFIES: this
    // EFFECTS: creates instance of GymSession
    public GymSession(ArrayList<GymExercise> g, String d) {
        listOfGymExercise = g;
        date = d;
    }

    // EFFECTS: totals up all the weight lifted multiplied by the respective sets
    public int totalWeightLifted() {

    }


    // MODIFIES: this
    // EFFECTS: adds an exercise to the session
    public void addExercise(GymExercise exercise) {

    }

    public ArrayList<GymExercise> getListOfGymExercise() {

    }

    public String getDate() {

    }
}
