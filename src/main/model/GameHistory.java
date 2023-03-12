package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
    private List<Game> history = new ArrayList<>();
    private List<String> finalMessages;
    private List<Integer> winnerCount;
    private List<TicTacToe> pastBoards;


    // MODIFY: this
    // EFFECTS: adds game to history
    public void addGame(Game game) {
        history.add(game);
    }

    // EFFECTS: returns the list of strings that represents message in the Game class
    public List<String> messages() {
        List<String> finalMessages = new ArrayList<>();
        for (Game i : history) {
            finalMessages.add(i.getMessage());
        }
        return finalMessages;
    }

    // EFFECTS: returns the list of integers that represents winner in the Game class
    public List<Integer> winners() {
        List<Integer> winnerCount = new ArrayList<>();
        for (Game i : history) {
            winnerCount.add(i.getWinner());
        }
        return winnerCount;
    }

    // // EFFECTS: returns the list of TicTacToe that represents board in the Game class
    public List<TicTacToe> boards() {
        List<TicTacToe> pastBoards = new ArrayList<>();
        for (Game i : history) {
            pastBoards.add(i.getBoard());
        }
        return pastBoards;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("messages", finalMessages);
        json.put("winners", winnerCount);
        json.put("boards", pastBoards);
        return json;




}
