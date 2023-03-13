package persistence;

import model.Game;
import model.GameHistory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.TicTacToe;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads GameHistory from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses GameHistory from JSON object and returns it
    private GameHistory parseGameHistory(JSONObject jsonObject) {
        GameHistory gh = new GameHistory();
        addGames(gh, jsonObject);
        return gh;
    }

    // MODIFIES: gh
    // EFFECTS: parses gameHistory from JSON object and adds them to GameHistory
    private void addGames(GameHistory gh, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("games");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(gh, nextGame);
        }
    }

    // MODIFIES: gh
    // EFFECTS: parses game from JSON object and adds it to Game
    private void addGame(GameHistory gh, JSONObject jsonObject) {
        String message = jsonObject.getString("message");
        int winner = jsonObject.getInt("winner");
        JSONObject board = jsonObject.getJSONObject("board");
        Game game = new Game();
        game.setMessage(message);
        game.setWinner(winner);
        game.setBoard(setTicTacToe(board));
        gh.addGame(game);
    }

    // EFFECTS: parses board from JSON and adds it to TicTacToe
    private TicTacToe setTicTacToe(JSONObject jsonObject) {
        TicTacToe board = new TicTacToe();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String value = jsonObject.getString(String.valueOf(i * 3 + j));
                board.changeBoard(i * 3 + j, value);
            }
        }
        return board;
    }


}