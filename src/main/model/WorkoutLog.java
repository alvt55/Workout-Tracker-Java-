package model;

import org.json.JSONArray;
import org.json.JSONObject;

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



    // REQUIRES: gymSessions.length > 0
    // MODIFIES: none
    // EFFECTS: counts all the gym sessions and returns
    public int amountOfGymSessions() {
        return gymSessions.size();
    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: returns date and weight of GymSession with most cumulative weight lifted
    // if same weight, takes the first occurrence
    public String mostWeightLifted() {
        int currWeight = 0;
        String currDate = "";


        for (GymSession g : gymSessions) {
            if (g.totalWeightLifted() > currWeight) {
                currWeight = g.totalWeightLifted();
                currDate = g.getDate();
            }
        }

        return "Most Weight Lifted\n" + "Date: " + currDate + "\n" + "Weight: " + currWeight;
    }



    // REQUIRES: PersonalBests is not empty
    // MODIFIES: n/a
    // EFFECTS: returns list of all personal bests with date and weight
    public String allPersonalBests() {
        String list = "";

        for (PersonalBest pb : personalBests) {
            list += pb.toString() + "\n";
        }

        return list;
    }

    // REQUIRES: gymSessions is not empty
    // MODIFIES: n/a
    // EFFECTS: returns list of all gym sessions
    public String allGymSessions() {
        String list = "";

        for (GymSession g : gymSessions) {
            list += g.toString() + "\n";
        }

        return list;
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("personalbests", personalBestsToJson());
        json.put("gymsessions", gymSessionsToJson());
        return json;
    }

    // EFFECTS: returns personalbests in this workoutlog as a JSON array
    private JSONArray personalBestsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PersonalBest p : personalBests) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns gymsessions in this workoutlog as a JSON array
    private JSONArray gymSessionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GymSession g : gymSessions) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }







}
