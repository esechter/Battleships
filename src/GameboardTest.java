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
        Assertions.assertEquals(true, testGame.isShip("user",8,9), "User ship already at 0, 0");
        Assertions.assertEquals(false, testGame.isShip("user", 5, 4), "No user ship at 5,4");
        Assertions.assertEquals(true, testGame.isShip("computer", 1, 1), "Computer ship at 1,1");
        Assertions.assertEquals(false, testGame.isShip("computer", 0,0), "No computer ship at 0, 0");
    }

    // this test only works if the method is made public
    /**
    @Test
    void validInputReturnsTrue() {
        // === given/when ===
        Game game = new Game();
        // === then ===
        Assertions.assertEquals(true, game.isValidCoordinate("5,5"), "5,5 is valid format");
        Assertions.assertEquals(true, game.isValidCoordinate("5, 5"), "5, 5 is valid format");
    }


    @Test
    void invalidInputReturnsFalse() {
        // === given/when===
        Game game = new Game();
        // === then ===
        Assertions.assertEquals(false, game.isValidCoordinate("QQQ"), "QQQ should not be valid");
        Assertions.assertEquals(false, game.isValidCoordinate("11,5"), "11,5 should not be valid");
        Assertions.assertEquals(false, game.isValidCoordinate("-1,5"), "-1,5 should now be valid");
    }
    **/
}