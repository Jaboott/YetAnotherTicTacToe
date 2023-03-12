package persistence;

import model.Game;
import model.GameHistory;
import model.TicTacToe;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDoesNotExist.json");
        try {
            GameHistory gh = reader.read();
            fail("Not supposed to reach here");
        } catch(IOException e) {

        }
    }


    @Test
    void testReaderEmptyGameHistory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameHistory.json");
        try {
            GameHistory gh = reader.read();
            assertEquals(0, gh.messages().size());
            assertEquals(0, gh.boards().size());
            assertEquals(0, gh.winners().size());
        } catch(IOException e) {
            fail("Not supposed to reach here");
        }
    }

    @Test
    void testReaderNonEmptyGameHistory() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyGameHistory.json");
        try {
            GameHistory gh = reader.read();
            assertEquals(2, gh.messages().size());
            assertEquals(2, gh.boards().size());
            assertEquals(2, gh.winners().size());

            assertEquals(1, gh.winners().get(0));
            assertEquals("Player1 have won by diagonal", gh.messages().get(0));
            assertEquals("X", gh.boards().get(0).getSlot(0,0));

            assertEquals(2, gh.winners().get(1));
            assertEquals("Player2 have won by column", gh.messages().get(1));
            assertEquals("O", gh.boards().get(1).getSlot(0,0));
        } catch(IOException e) {
            fail("Not supposed to reach here");
        }
    }


}
