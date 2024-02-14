package model;

// represents a single gym exercise, will be added to a GymWorkout
public class GymExercise {
    private String name;
    private int weight; // in lbs, 0 if body weight exercise
    private int bodyWeight; // in lbs, 0 if weight lifting exercise, actual body weight lifted
    private int sets; // amount of sets done
    private int reps; // repetitions per set


    // MODIFIES: this
    // EFFECTS: creates a GymExercise object with parameters set to fields
    public GymExercise(String n, int w, int bw, int s, int r) {
        name = n;
        weight = w;
        bodyWeight = bw;
        sets = s;
        reps = r;
    }

    // REQUIRES: one of bodyWeight or weight must be 0
    // MODIFIES: none
    // EFFECTS: returns the total weight lifted for this exercise

    public int getTotalWeight() {

        if (bodyWeight == 0) {
            return reps * sets * weight; // weight lifting exercise
        }

        return reps * sets * bodyWeight; // body weight exercise
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getBodyWeight() {
        return bodyWeight;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    // EFFECTS: returns summary
    public String toString() {
        if (bodyWeight == 0) {
            return sets + "x" + reps + " (" + weight + ") " + name;
        }

        return sets + "x" + reps + " (bodyweight) " + bodyWeight;
    }





}
