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
    }

    @Test
    void testToString() {
    }
}