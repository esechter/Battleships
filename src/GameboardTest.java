import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameboardTest {

    @Test
    void isOnBoard() {
        // === given/when/then ===
        Gameboard testGame = new Gameboard();
        Assertions.assertEquals(true, testGame.isOnBoard(0,0), "0,0 is on the board");
        Assertions.assertEquals(true, testGame.isOnBoard(9,9), "9, 9 is on the board");
        Assertions.assertEquals(false, testGame.isOnBoard(0, 10), "0, 10 is not on the board");
        Assertions.assertEquals(false, testGame.isOnBoard(200, 0), "200, 0 is not on the board");
        Assertions.assertEquals(false, testGame.isOnBoard(2000, 2000), "2000, 2000 is not on the board");
        Assertions.assertEquals(false, testGame.isOnBoard(-10, 0), "-10, 0 is not on the board");
    }

    @Test
    void isAShip() {
        // === given ===
        Gameboard testGame = new Gameboard();
        testGame.addShip("user",0, 0);
        testGame.addShip("user",8,9);
        testGame.addShip("computer", 1,1);
        testGame.addShip("computer", 2, 2);
        // === when/then ===
        Assertions.assertEquals(true, testGame.isAShip("user",8,9), "User ship already at 0, 0");
        Assertions.assertEquals(false, testGame.isAShip("user", 5, 4), "No user ship at 5,4");
        Assertions.assertEquals(true, testGame.isAShip("computer", 1, 1), "Computer ship at 1,1");
        Assertions.assertEquals(false, testGame.isAShip("computer", 0,0), "No computer ship at 0, 0");
    }
}