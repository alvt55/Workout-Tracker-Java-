package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.io.Writer;
import java.util.ArrayList;

// NOTE: some parts based on JsonSerializationDemo
// represents a collection of gym exercises and info about the workout
public class GymSession implements Writable {
    private String date; // month day, year
    private ArrayList<GymExercise> listOfGymExercise;



    // MODIFIES: this
    // EFFECTS: creates instance of GymSession
    public GymSession(ArrayList<GymExercise> g, String d) {
        date = d;
        listOfGymExercise = g;
    }

    // EFFECTS: totals up and returns all the weight lifted in the session
    public int totalWeightLifted() {
        int total = 0;

        for (GymExercise g : listOfGymExercise) {
            total += g.getTotalWeight();
        }

        return total;
    }


    // MODIFIES: this
    // EFFECTS: adds an exercise to the session - logs event
    public void addExercise(GymExercise exercise) {
        listOfGymExercise.add(exercise);
        EventLog.getInstance().logEvent(new Event("Added " + exercise.getName() + " to " + date));
    }

    public ArrayList<GymExercise> getListOfGymExercise() {
        return listOfGymExercise;
    }

    public String getDate() {
        return date;
    }

    // EFFECTS: return summary
    public String toString() {
        String answer = getDate() + "\n";

        for (GymExercise g : listOfGymExercise) {
            answer += g.toString() + "\n";

        }

        return answer;
    }

    @Override
    // returns json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("listofgymexercises", gymExercisesToJson());
        return json;
    }

    // EFFECTS: returns GymExercises in this session as a JSON array
    private JSONArray gymExercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GymExercise g : listOfGymExercise) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }


}
