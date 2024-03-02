package persistence;

import model.Category;
import model.GymSession;
import model.PersonalBest;
import model.Thingy;
import model.WorkRoom;

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
        ArrayList<PersonalBest> personalBests = (ArrayList<PersonalBest>) jsonObject.get("personalbests");
        ArrayList<GymSession> gymSessions = (ArrayList<GymSession>) jsonObject.get("gymsessions");
        WorkoutLog wl = new WorkoutLog(personalBests, gymSessions);
        addPersonalBests(wl, jsonObject);
        return wl;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addPersonalBests(WorkoutLog wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("personalbests");
        for (Object json : jsonArray) {
            JSONObject nextPB = (JSONObject) json;
            addPersonalBest(wl, nextPB);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addPersonalBest(WorkoutLog wl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        int weight = jsonObject.getInt("weight");
        PersonalBest pb = new PersonalBest(name, date, weight);
        wl.addPersonalBest(pb);
    }
}


