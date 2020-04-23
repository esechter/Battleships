import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameboardStateTest {

    @Test
    void testGetValueIsValid() {
        Assertions.assertEquals('0', GameboardState.EMPTY.getValue(),"Value of EMPTY must be 0");
        Assertions.assertEquals('5', GameboardState.PLAYERMISSED.getValue(),"Value of EMPTY must be 0");
    }

    @Test
    void TestIsEnum() {
        Assertions.assertEquals(true, GameboardState.isEnum('1'), "1 is an Enum");
        Assertions.assertEquals(true, GameboardState.isEnum('5'), "5 is an Enum");
        Assertions.assertEquals(false, GameboardState.isEnum('a'), "a isn't an Enum");
        Assertions.assertEquals(false, GameboardState.isEnum('6'), "6 isn't an Enum");
    }

    @Test
    void getEnumFromValue() {
        Assertions.assertEquals(GameboardState.EMPTY, GameboardState.getEnumFromValue(0), "0 is the constant for EMPTY");
        Assertions.assertEquals(GameboardState.PLAYERBOAT, GameboardState.getEnumFromValue(1), "1 is the constant for EMPTY");
        Assertions.assertEquals(GameboardState.COMPUTERBOAT, GameboardState.getEnumFromValue(2), "2 is the constant for EMPTY");
        Assertions.assertEquals(GameboardState.SUNKPLAYERBOAT, GameboardState.getEnumFromValue(3), "3 is the constant for EMPTY");
        Assertions.assertEquals(GameboardState.SUNKCOMPUTERBOAT, GameboardState.getEnumFromValue(4), "4 is the constant for EMPTY");
        Assertions.assertEquals(GameboardState.PLAYERMISSED, GameboardState.getEnumFromValue(5), "5 is the constant for EMPTY");
    }

    @Test
    void testToString() {
        Assertions.assertEquals(" ", GameboardState.EMPTY.toString(), "Empty symbol is ' '");
        Assertions.assertEquals("@", GameboardState.PLAYERBOAT.toString(), "Player boat symbol is '@'");
    }
}