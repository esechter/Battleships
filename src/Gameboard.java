import java.util.ArrayList;
import java.util.HashSet;

public class Gameboard {
    private static final int BOARDSIZE = 10;
    private HashSet<ArrayList<Integer>> userMoves = new HashSet<ArrayList<Integer>>();
    private HashSet<ArrayList<Integer>> computerMoves = new HashSet<ArrayList<Integer>>();
    private HashSet<ArrayList<Integer>> userShips = new HashSet<ArrayList<Integer>>();
    private HashSet<ArrayList<Integer>> computerShips = new HashSet<ArrayList<Integer>>();
    private int[][] map;
    private boolean isWon = false;
    private boolean isLost = false;

    public Gameboard() {
        map = new int[BOARDSIZE][BOARDSIZE];
    }

    public int getBoardsize() {
        return BOARDSIZE;
    }

    public boolean isWon() {
        return isWon;
    }

    public boolean isLost() {
        return isLost;
    }

    public boolean isOnBoard(int X, int Y) {
        return (X >= 0 && X <= BOARDSIZE - 1 && Y >= 0 && Y <= BOARDSIZE - 1);
    }

    public boolean isAShip(String player, int X, int Y) {
        boolean ship = false;
        switch (player) {
            case "computer":
                if (computerShips.contains(putIntsInArray(X, Y))) {
                    return true;
                }
                break;
            case "user":
                if (userShips.contains(putIntsInArray(X, Y))) {
                    return true;
                }
                break;
        }
        return false;
    }

    private ArrayList<Integer> putIntsInArray(int X, int Y) {
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        tempList.add(X);
        tempList.add(Y);
        return tempList;
    }

    public void addShip(String player, int X, int Y) {
        if (player.equals("user")) {
            userShips.add(putIntsInArray(X, Y));
            map[X][Y] = GameboardState.PLAYERBOAT.getValue();
        }
        if (player.equals("computer")) {
            computerShips.add(putIntsInArray(X, Y));
            map[X][Y] = GameboardState.COMPUTERBOAT.getValue();
        }
    }

    public void removeShip(String player, int X, int Y) {
        if (player.equals("user")) {
            userShips.remove(putIntsInArray(X, Y));
        }
        if (player.equals("computer")){
            computerShips.remove((putIntsInArray(X, Y)));
        }
    }

    @Override
    public String toString() {
        String topAndBottomLine = "   0123456789   \n";
        StringBuilder mapString = new StringBuilder();
        mapString.append(topAndBottomLine);
        for (int row = 0; row < map.length; row++) {
            mapString.append(row + " |");
            for (int column = 0; column < map[row].length; column++) {
                mapString.append(GameboardState.getEnumFromValue(map[row][column]).toString());
            }
            mapString.append("| " + row + "\n");
        }
        mapString.append(topAndBottomLine);
        return mapString.toString();
    }
}
