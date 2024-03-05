package model;

import org.json.JSONObject;
import persistence.Writable;

// NOTE: some parts based on JsonSerializationDemo
// represents a single gym exercise, will be added to a GymWorkout
public class GymExercise implements Writable {
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
            return getSets() + "x" + getReps() + " (" + getWeight() + ") " + getName();
        }

        return getSets() + "x" + getReps() + " (bodyweight) " + getBodyWeight();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weight", weight);
        json.put("bodyweight", bodyWeight);
        json.put("reps", reps);
        json.put("sets", sets);
        return json;
    }
}





