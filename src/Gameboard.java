import java.util.HashSet;
import java.util.List;

public class Gameboard {
    private HashSet<List<Integer>> computerMoves = new HashSet<List<Integer>>();
    //private HashSet<List<Integer>> userMoves = new HashSet<List<Integer>>();
    private char[][] map;
    private boolean isGameWon = false;

    public Gameboard() {
        map = new char[10][10];
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    @Override
    public String toString() {
        String topAndBottomLine = "   0123456789   \n";
        StringBuilder mapString = new StringBuilder();
        mapString.append(topAndBottomLine);
        for (int row = 0; row < map.length; row++) {
            mapString.append(row + " |");
            for (int column = 0; column < map[row].length; column++) {
                mapString.append(map[row][column]);
                /**switch(map[row][column]) {
                 case PLAYERBOAT:
                 mapString.append(PRINTPLAYERBOAT);
                 break;
                 case SUNKPLAYERBOAT:
                 mapString.append((PRINTSUNKPLAYERBOAT));
                 break;
                 default:
                 mapString.append(" ");
                 }**/
            }
            mapString.append("| " + row + "\n");
        }
        mapString.append(topAndBottomLine);
        return mapString.toString();
    }
}
