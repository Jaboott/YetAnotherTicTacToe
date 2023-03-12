package persistence;

import model.Game;
import model.GameHistory;
import model.TicTacToe;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            GameHistory gh = new GameHistory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("not supposed to happen");
        } catch (IOException e) {

        }
    }

    @Test
    void testWriterEmptyGameHistory() {
        try {
            GameHistory gh = new GameHistory();
            JsonWriter writer = new JsonWriter("./data/testGameHistory.json");
            writer.open();
            writer.write(gh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGameHistory.json");
            gh = reader.read();
            assertEquals(0, gh.messages().size());
            assertEquals(0, gh.boards().size());
            assertEquals(0, gh.winners().size());
        } catch (IOException e) {
            fail("not supposed to happen");
        }
    }

    @Test
    void testWriter1GameHistory() {
        try {
            GameHistory gh = new GameHistory();
            Game game = new Game();
            TicTacToe ticTacToe = new TicTacToe();
            ticTacToe.changeBoard(0,"X");
            game.setWinner(1);
            game.setMessage("1 have won");
            game.setBoard(ticTacToe);
            gh.addGame(game);

            JsonWriter writer = new JsonWriter("./data/testGameHistory.json");
            writer.open();
            writer.write(gh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGameHistory.json");
            gh = reader.read();
            assertEquals(1, gh.messages().size());
            assertEquals(1, gh.boards().size());
            assertEquals(1, gh.winners().size());

            assertEquals(1, gh.winners().get(0));
            assertEquals("1 have won", gh.messages().get(0));
            assertEquals("X", gh.boards().get(0).getSlot(0,0));

        } catch (IOException e) {
            fail("not supposed to happen");
        }
    }

    @Test
    void testWriter2GameHistory() {
        try {
            GameHistory gh = new GameHistory();
            Game game1 = new Game();
            Game game2 = new Game();
            TicTacToe ticTacToe1 = new TicTacToe();
            TicTacToe ticTacToe2 = new TicTacToe();

            ticTacToe1.changeBoard(0,"X");
            ticTacToe2.changeBoard(0, "O");
            game1.setWinner(1);
            game1.setMessage("1 have won");
            game1.setBoard(ticTacToe1);
            game2.setWinner(2);
            game2.setMessage("2 have won");
            game2.setBoard(ticTacToe2);
            gh.addGame(game1);
            gh.addGame(game2);

            JsonWriter writer = new JsonWriter("./data/testGameHistory.json");
            writer.open();
            writer.write(gh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGameHistory.json");
            gh = reader.read();
            assertEquals(2, gh.messages().size());
            assertEquals(2, gh.boards().size());
            assertEquals(2, gh.winners().size());

            assertEquals(1, gh.winners().get(0));
            assertEquals("1 have won", gh.messages().get(0));
            assertEquals("X", gh.boards().get(0).getSlot(0,0));

            assertEquals(2, gh.winners().get(1));
            assertEquals("2 have won", gh.messages().get(1));
            assertEquals("O", gh.boards().get(1).getSlot(0,0));

        } catch (IOException e) {
            fail("not supposed to happen");
        }
    }
}
