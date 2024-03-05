package model;

import org.json.JSONObject;
import persistence.Writable;

import java.io.Writer;

// NOTE: some parts based on JsonSerializationDemo
// represents a personal best set for a single repetition including the weight, name of exercise and date
public class PersonalBest implements Writable {

    private String name; // name of exercise
    private String date; // month day, year
    private int weight; // weight in lbs


    // MODIFIES: this
    // EFFECTS: creates GymPersonalBestAttempt object
    public PersonalBest(String n, String d, int w) {
        name = n;
        date = d;
        weight = w;
    }


    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getWeight() {
        return weight;
    }

    // REQUIRES: none
    // MODIFIES: none
    // EFFECTS: returns summary along with name, date and weight lifted
    public String toString() {
        return "Personal Best: " + name + " - " + weight + "lbs on " + date;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("datePB", date);
        json.put("weight", weight);
        return json;
    }




}
