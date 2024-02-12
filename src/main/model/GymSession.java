package model;

import java.util.ArrayList;

// represents a collection of gym exercises and info about the workout
public class GymSession {
    private String date; // month day, year
    private ArrayList<GymExercise> listOfGymExercise;



    // MODIFIES: this
    // EFFECTS: creates instance of GymSession
    public GymSession(ArrayList<GymExercise> g, String d) {
        date = d;
        listOfGymExercise = g;
    }

    // EFFECTS: totals up all the weight lifted in the session
    public int totalWeightLifted() {
        int total = 0;

        for (GymExercise g : listOfGymExercise) {
            total += g.getTotalWeight();
        }

        return total;
    }


    // MODIFIES: this
    // EFFECTS: adds an exercise to the session
    public void addExercise(GymExercise exercise) {
        listOfGymExercise.add(exercise);
    }

    public ArrayList<GymExercise> getListOfGymExercise() {
        return listOfGymExercise;
    }

    public String getDate() {
        return date;
    }


}
