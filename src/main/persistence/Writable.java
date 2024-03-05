package persistence;

import org.json.JSONObject;
// NOTE: based on JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
