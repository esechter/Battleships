import java.util.ArrayList;
import java.util.HashSet;

public class Gameboard {
    private static final int BOARD_SIZE = 5;
    private static final int NUMBER_OF_SHIPS = 5;
    private HashSet<ArrayList<Integer>> userMoves = new HashSet<ArrayList<Integer>>();
    private HashSet<ArrayList<Integer>> computerMoves = new HashSet<ArrayList<Integer>>();
    private HashSet<ArrayList<Integer>> userShips = new HashSet<ArrayList<Integer>>();
    private HashSet<ArrayList<Integer>> computerShips = new HashSet<ArrayList<Integer>>();
    private int[][] map;
    private boolean isWon = false;
    private boolean isLost = false;

    public Gameboard() {
        map = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public int getNumberOfShips() {
        return NUMBER_OF_SHIPS;
    }

    public boolean isWon() {
        return isWon;
    }

    public boolean isLost() {
        return isLost;
    }

    public boolean isOnBoard(int X, int Y) {
        return (X >= 0 && X <= BOARD_SIZE - 1 && Y >= 0 && Y <= BOARD_SIZE - 1);
    }

    public boolean isShip(String player, int X, int Y) {
        switch (player) {
            case "computer": //checks if this is location is a computer ship
                if (computerShips.contains(putIntsInArray(X, Y))) {
                    return true;
                }
                break;
            case "user": //checks if this location is a user ship
                if (userShips.contains(putIntsInArray(X, Y))) {
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean isAlreadyGuessed(String player, int X, int Y) {
        if (player.equals("user")) {
            if (userMoves.contains(putIntsInArray(X, Y))) {
                return true;
            }
        }
        if (player.equals("computer")) {
            if (computerMoves.contains(putIntsInArray(X, Y))) {
                return true;
            }
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

    public void sinkShip(int X, int Y) {
        if (map[X][Y] == GameboardState.PLAYERBOAT.getValue()) {
            removeShip("user", X, Y);
            map[X][Y] = GameboardState.SUNKPLAYERBOAT.getValue();
        }
        if (map[X][Y] == GameboardState.COMPUTERBOAT.getValue()) {
            removeShip("computer", X, Y);
            map[X][Y] = GameboardState.SUNKCOMPUTERBOAT.getValue();
        }
        if (userShips.isEmpty()) {
            this.isLost = true;
        }
        if (computerShips.isEmpty()) {
            this.isWon = true;
        }
    }

    public int getShipsRemaining(String player) {
        if (player.equals("user")) {
            return this.userShips.size();
        }
        if (player.equals("computer")) {
            return this.computerShips.size();
        }
        return -1;
    }

    public void addMove(String player, int X, int Y) {
        if (player.equals(("user"))) {
            userMoves.add(putIntsInArray(X, Y));
        }
        if (player.equals("computer")) {
            computerMoves.add(putIntsInArray(X, Y));
        }
    }

    public HashSet<ArrayList<Integer>> getMoveList(String player) {
        if (player.equals("user")) {
            return this.userMoves;
        }
        if (player.equals("computer")) {
            return this.computerMoves;
        }
        return null;
    }

    public void addPlayerMiss(int X, int Y) {
        map[X][Y] = GameboardState.PLAYERMISSED.getValue();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int i = 0; i < BOARD_SIZE; i++) {
            sb.append(i);
        }
        sb.append("\n");
        String topAndBottomLine = sb.toString();
        StringBuilder mapString = new StringBuilder("\n");
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
