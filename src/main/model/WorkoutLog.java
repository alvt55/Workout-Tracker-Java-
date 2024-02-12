package model;

import java.util.ArrayList;

// stores all GymSessions and GymPersonalBests
public class WorkoutLog {

    private ArrayList<PersonalBest> personalBests;
    private ArrayList<GymSession> gymSessions;

    // EFFECTS: constructor for creating log with workouts already present
    public WorkoutLog(ArrayList<PersonalBest> pb, ArrayList<GymSession> g) {
        personalBests = pb;
        gymSessions = g;
    }

    // EFFECTS: default constructor
    public WorkoutLog() {
        personalBests = new ArrayList<PersonalBest>();
        gymSessions = new ArrayList<GymSession>();
    }


    // REQUIRES: gymSessions.length > 0
    // MODIFIES: none
    // EFFECTS: counts all the gym sessions and returns
    public int amountOfGymSessions() {
        return gymSessions.size();
    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: returns date and weight of personal best OR GymSession with most cumulative weight lifted
    public String mostWeightLifted() {
        int currWeight = 0;
        String currDate = "";

        for (PersonalBest pb : personalBests) {
            if (pb.getWeight() > currWeight) {
                currWeight = pb.getWeight();
                currDate = pb.getDate();
            }
        }

        for (GymSession g : gymSessions) {
            if (g.totalWeightLifted() > currWeight) {
                currWeight = g.totalWeightLifted();
                currDate = g.getDate();
            }
        }

        return "Most Weight Lifted\n" + "Date: " + currDate + "\n" + "Weight: " + currWeight;
    }


    // MODIFIES: this
    // EFFECTS: adds a gym personal best to log
    public void addPersonalBest(PersonalBest pb) {
        personalBests.add(pb);
    }

    // MODIFIES: this
    // EFFECTS: adds a gym session to log
    public void addGymSession(GymSession g) {
        gymSessions.add(g);

    }







}
