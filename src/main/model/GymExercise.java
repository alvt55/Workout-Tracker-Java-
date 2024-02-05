package model;

// represents a single gym exercise, will be added to a GymWorkout
public class GymExercise {
    private int name;
    private int weight;
    private boolean isBodyWeight; // if the exercise is purely body weight (ex. pull-ups)
    private int sets; // amount of sets done
    private int reps; // repetitions per set


    // MODIFIES: this
    // EFFECTS: creates a GymExercise object with parameters set to fields
    public GymExercise(int n, int w, boolean bw, int s, int r) {
        name = n;
        weight = w;
        isBodyWeight = bw;
        sets = s;
        reps = r;
    }


    public int getName() {

    }

    public int getWeight() {

    }

    public boolean getIsBodyWeight() {

    }

    public int getSets() {

    }

    public int getReps() {

    }




}
