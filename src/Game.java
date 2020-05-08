import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private int SLEEP_TIME = 1200;
    private boolean quitGame;
    private Scanner scanner;
    private Gameboard gameboard;

    public Game() {
        gameboard = new Gameboard();
        quitGame = false;
    }

    public void playGame() {
        System.out.println("*** Welcome to Battleships Game! ***\n");
        System.out.println("Starting a new game! Enter 'Q' any time to quit.");
        waitForReadability();
        System.out.println(gameboard.toString());
        System.out.println("The seas are empty!");
        scanner = new Scanner(System.in);
        placeUserShips();
        if (!quitGame) {
            placeComputerShips();
            attack();
        }
        if (quitGame || gameboard.isWon() || gameboard.isLost()) endGame();
    }

    private void placeUserShips() {
        System.out.printf("Place %d ships. Provide X, Y coordinates for each ship.\n", gameboard.getNumberOfShips());
        int count = 0;
        while (count < gameboard.getNumberOfShips() && !quitGame) {
            String input = getCoordinateFromUser(scanner);
            if (input.toLowerCase().equals("q")) {
                quitGame = true;
            } else {
                int[] coordinates = getCoordinateArrayFromString(input);
                if (gameboard.isShip("user", coordinates[0], coordinates[1])) {
                    System.out.print("You already have a ship at " + input + ". Please choose another location. ");
                    continue;
                }
                gameboard.addShip("user", coordinates[0], coordinates[1]);
                count++;
                System.out.printf("Ship %d placed.\n", count);
            }
        }
    }

    private void placeComputerShips() {
        System.out.printf("Computer is deploying %d ships... ", gameboard.getNumberOfShips());
        waitForReadability();
        int computerShipCount = 0;
        while (computerShipCount < gameboard.getNumberOfShips()) {
            Random random = new Random();
            int X = random.nextInt(gameboard.getBoardSize());
            int Y = random.nextInt(gameboard.getBoardSize());
            if (gameboard.isOnBoard(X, Y) && !gameboard.isShip("computer", X, Y) && !gameboard.isShip("user", X, Y)) {
                gameboard.addShip("computer", X, Y);
                computerShipCount++;
            }
        }
        System.out.println("Computer's ships are deployed.");
    }

    private void attack() {
        while (!quitGame && !gameboard.isWon() && !gameboard.isLost()) {
            waitForReadability();
            System.out.println(gameboard.toString());
            System.out.printf("Your ships remaining: %d | Computer ships remaining: %d\n",
                gameboard.getShipsRemaining("user"), gameboard.getShipsRemaining("computer"));
            System.out.println("\nYOUR TURN");
            System.out.println("Choose a coordinate to attack!");
            String input = getCoordinateFromUser(scanner);
            if (input.toLowerCase().equals("q")) {
                quitGame = true;
                return;
            } else {
                int[] coordinates = getCoordinateArrayFromString(input);
                if (gameboard.isAlreadyGuessed("user", coordinates[0], coordinates[1])) {
                    System.out.printf("You've already guessed %d, %d. Please choose another location.\n", coordinates[0], coordinates[1]);
                    continue;
                } else {
                    updateBoard("user", coordinates[0], coordinates[1]);
                }
            }
            if (gameboard.isWon() || gameboard.isLost()) return;
            waitForReadability();
            System.out.println("\nCOMPUTER'S TURN");
            boolean madeAValidGuess = false;
            while (!madeAValidGuess) {
                Random random = new Random();
                int X = random.nextInt(gameboard.getBoardSize());
                int Y = random.nextInt(gameboard.getBoardSize());
                if (gameboard.isOnBoard(X, Y) && !gameboard.isAlreadyGuessed("computer", X, Y)) {
                    madeAValidGuess = true;
                    updateBoard("computer", X, Y);
                }
            }
            if (gameboard.isWon() || gameboard.isLost()) return;
        }
    }

    private void updateBoard(String player, int X, int Y) {
        if (player.equals("user")) {
            gameboard.addMove("user", X, Y);
            if (gameboard.isShip("computer", X, Y) || gameboard.isShip("user", X, Y)) {
                gameboard.sinkShip(X, Y);
                System.out.printf("Direct hit! Ship at %d, %d is sunk\n", X, Y);
            } else {
                System.out.printf("Nothing at %d, %d! You missed.\n", X, Y);
                gameboard.addPlayerMiss(X, Y);
            }
        } else if(player.equals("computer")) {
            gameboard.addMove("computer", X, Y);
            if (gameboard.isShip("user", X, Y) || gameboard.isShip("computer", X, Y)) {
                gameboard.sinkShip(X, Y);
                System.out.printf("Computer sunk ship at %d, %d!\n", X, Y);
            } else {
                System.out.println("Computer missed.");
            }
        }
    }

    private void endGame() {
        scanner.close();
        waitForReadability();
        if (quitGame) {
            System.out.println("Quitting game...\nThanks for playing! Goodbye.");
        }
        if (gameboard.isWon()) {
            System.out.println(gameboard.toString());
            System.out.println("You win! Congratulations :)");
        }
        if (gameboard.isLost()) {
            System.out.println(gameboard.toString());
            System.out.println("You lose :(");
        }
    }

    public boolean isValidCoordinate(String input){
        if(input.matches("\\d+, ?\\d+")) {
            int[] coordinates = getCoordinateArrayFromString(input);
            return gameboard.isOnBoard(coordinates[0], coordinates[1]);
        }
        return input.toLowerCase().equals("q");
    }

    private int[] getCoordinateArrayFromString(String input) {
        String[] inputArray = input.split(",");
        int[] coordinates = new int[2];
        try {
            coordinates[0] = Integer.parseInt(inputArray[0].trim());
            coordinates[1] = Integer.parseInt(inputArray[1].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Couldn't parse Integers from user input"); // I've read you're not supposed to re-throw exceptions but I'm not sure what the right way to handle this is yet
        }
        return coordinates;
    }

    private String getCoordinateFromUser(Scanner sc) {
        String prompt = "Provide a coordinate within board in 'X, Y' format, or 'Q' to quit: ";
        System.out.print(prompt);
        String input = sc.nextLine();
        while (!isValidCoordinate(input)) {
            System.out.print("Invalid input. " + prompt);
            input= sc.nextLine();
        }
        return input;
    }

    private void waitForReadability() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getMoveList(String player) {
        StringBuilder moveList = new StringBuilder();
        moveList.append("[");
        for (ArrayList<Integer> i : gameboard.getMoveList("computer")) {
            moveList.append("(");
            moveList.append(i.get(0));
            moveList.append(", ");
            moveList.append(i.get(1));
            moveList.append(") ");
        }
        moveList.append("]");
        return moveList.toString();
    }
}