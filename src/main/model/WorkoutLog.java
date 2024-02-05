package model;

import java.util.ArrayList;

// stores all types of workouts
public class WorkoutLog {

    private ArrayList<GymPersonalBest> gymPersonalBests;
    private ArrayList<GymSession> gymSessions;
    private ArrayList<SwimSession> swimSessions;

    public WorkoutLog(ArrayList<GymPersonalBest> pb, ArrayList<GymSession> g, ArrayList<SwimSession> s) {
        gymPersonalBests = pb;
        gymSessions = g;
        swimSessions = s;

    }


   // REQUIRES: gymSessions.length > 0
    // MODIFIES: none
    // EFFECTS: counts all the gym sessions and returns
    public int amountOfGymSessions() {

    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: searches through personal bests and gym session and returns date with most cumulative weight lifted
    public String mostWeightLifted() {

    }


    // MODIFIES: this
    // EFFECTS: adds a gym personal best to log
    public void addGymPersonalBest(GymPersonalBest pb) {


    }

    // MODIFIES: this
    // EFFECTS: adds a gym session to log
    public void addGymSession(GymSession g) {


    }

    // MODIFIES: this
    // EFFECTS: adds a swim session to log
    public void addSwimSession(SwimSession pb) {


    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: return summaries of all gym personal bests
    public String getAllGymPersonalBests() {

    }



}
