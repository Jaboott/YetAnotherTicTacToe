package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class GameHistoryTest {

   GameHistory gameHistory;
   Game game;

    @BeforeEach
    void runBefore() {
        gameHistory = new GameHistory();
        game = new Game();
    }

    @Test
    void testEmpty() {
        List<String> messagesList = gameHistory.messages();
        List<Integer> winnersList = gameHistory.winners();
        List<TicTacToe> boardsList = gameHistory.boards();

        assertTrue(messagesList.isEmpty());
        assertTrue(winnersList.isEmpty());
        assertTrue(boardsList.isEmpty());
    }

    @Test
    void testNotEmpty() {
        game.setBoard(new TicTacToe());
        game.setWinner(1);
        game.setMessage("");

        assertEquals(new TicTacToe().getSlot(0,0), game.getBoard().getSlot(0,0));
        assertEquals(1, game.getWinner());
        assertEquals("", game.getMessage());
        gameHistory.addGame(game);

        List<String> messagesList = gameHistory.messages();
        List<Integer> winnersList = gameHistory.winners();
        List<TicTacToe> boardsList = gameHistory.boards();

        assertFalse(messagesList.isEmpty());
        assertFalse(winnersList.isEmpty());
        assertFalse(boardsList.isEmpty());

        assertEquals(1,winnersList.get(0));
        assertEquals("", messagesList.get(0));
        assertEquals(new TicTacToe().getSlot(0,0), boardsList.get(0).getSlot(0,0));
    }
}
