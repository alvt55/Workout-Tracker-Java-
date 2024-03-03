package persistence;


import model.GymExercise;
import model.GymSession;
import model.PersonalBest;


import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.WorkoutLog;
import org.json.*;
// citation
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workoutlog from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkoutLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workoutlog from JSON object and returns it
    private WorkoutLog parseWorkoutLog(JSONObject jsonObject) {
        ArrayList<PersonalBest> personalBests = new ArrayList<PersonalBest>();     //(ArrayList<PersonalBest>) jsonObject.get("personalbests");
        ArrayList<GymSession> gymSessions = new ArrayList<GymSession>();     //(ArrayList<GymSession>) jsonObject.get("gymsessions");
        WorkoutLog wl = new WorkoutLog(personalBests, gymSessions);
        addPersonalBests(wl, jsonObject);
        addGymSessions(wl, jsonObject);
        return wl;
    }

    private void addGymSessions(WorkoutLog wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("gymsessions");
        for (Object json : jsonArray) {
            JSONObject nextGS = (JSONObject) json;
            addGymSession(wl, nextGS);
        }
    }

    private void addGymSession(WorkoutLog wl, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        ArrayList<GymExercise> ge = new ArrayList<GymExercise>();
        GymSession gs = new GymSession(ge, date);
        addGymExercises(gs, jsonObject);
        wl.addGymSession(gs);
    }

    private void addGymExercises(GymSession s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listofgymexercises");
        for (Object json : jsonArray) {
            JSONObject nextEX = (JSONObject) json;
            addGymExercise(s, nextEX);
        }
    }

    private void addGymExercise(GymSession s, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int weight = jsonObject.getInt("weight");
        int bodyWeight = jsonObject.getInt("bodyweight");
        int reps = jsonObject.getInt("reps");
        int sets = jsonObject.getInt("sets");
        GymExercise ge = new GymExercise(name, weight, bodyWeight, reps, sets);
        s.addExercise(ge);
    }



    // MODIFIES: wr
    // EFFECTS: parses personalbests from JSON object and adds them to workoutlog
    private void addPersonalBests(WorkoutLog wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("personalbests");
        for (Object json : jsonArray) {
            JSONObject nextPB = (JSONObject) json;
            addPersonalBest(wl, nextPB);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses personalbest from JSON object and adds it to workoutlog
    private void addPersonalBest(WorkoutLog wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("datePB");
        int weight = jsonObject.getInt("weight");
        PersonalBest pb = new PersonalBest(name, date, weight);
        wl.addPersonalBest(pb);
    }





}


