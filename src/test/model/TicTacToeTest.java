package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    TicTacToe game;

    @BeforeEach
    void runBefore() {
        game = new TicTacToe();
    }

    @Test
    void checkStatusEmptyTest() {
        String status = game.checkStatus();
        assertEquals("", status);
        assertFalse(game.slotTaken(0));
    }

    @Test
    void checkStatusRowsTest() {
        game.changeBoard(0, "X");
        game.changeBoard(1, "X");
        game.changeBoard(2, "X");
        assertEquals("X", game.getSlot(0,0));
        assertEquals("X", game.getSlot(0,1));
        assertEquals("X", game.getSlot(0,2));
        assertEquals("row", game.checkStatus());
    }

    @Test
    void checkStatusColumnTest() {
        game.changeBoard(0, "O");
        game.changeBoard(3, "O");
        game.changeBoard(6, "O");
        assertEquals("O", game.getSlot(0,0));
        assertEquals("O", game.getSlot(1,0));
        assertEquals("O", game.getSlot(2,0));
        assertEquals("column", game.checkStatus());
    }

    @Test
    void checkStatusDiagonalTest() {
        game.changeBoard(0, "O");
        game.changeBoard(4, "O");
        game.changeBoard(8, "O");
        assertEquals("O", game.getSlot(0,0));
        assertEquals("O", game.getSlot(1,1));
        assertEquals("O", game.getSlot(2,2));
        assertEquals("diagonal", game.checkStatus());
    }

    @Test
    void checkStatusTieTest() {
        game.changeBoard(0, "X");
        game.changeBoard(1, "O");
        game.changeBoard(2, "X");
        game.changeBoard(3, "X");
        game.changeBoard(4, "X");
        game.changeBoard(5, "O");
        game.changeBoard(6, "O");
        game.changeBoard(7, "X");
        game.changeBoard(8, "O");
        assertEquals("X", game.getSlot(0,0));
        assertEquals("O", game.getSlot(0,1));
        assertEquals("X", game.getSlot(0,2));
        assertEquals("It is a tie", game.checkStatus());
    }


}