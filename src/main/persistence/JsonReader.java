package persistence;

import java.io.FileNotFoundException;

public class JsonReader {

    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonReader(String destination) {
        this.destination = destination;
    }
}
