import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private int SLEEP_TIME = 1200;
    private boolean quitGame = false;
    private Scanner scanner;
    private Gameboard gameboard;

    public void playGame() {
        gameboard = new Gameboard();
        System.out.println("*** Welcome to Battleships Game! ***\n");
        System.out.println("Enter 'S' to start a new game. Enter 'Q' any time to quit.");
        scanner = new Scanner(System.in);
        do {
            String input = scanner.next().toLowerCase();
            if (input.equals("q")) {
                quitGame = true;
            } else if (input.equals("s")) {
                System.out.println("Starting game...");
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(gameboard.toString());
                System.out.println("The seas are empty!");
                break;
            } else {
                System.out.println("Try again: 'S' to start a new game or 'Q' to quit");
            }
        } while(!quitGame);
        if (!quitGame) getAndPlaceShips();
        if (!quitGame) System.out.println(gameboard.toString());
        if (!quitGame) attackSequence();
        if (quitGame || gameboard.isWon() || gameboard.isLost()) {
            endGame();
        }
    }

    private void getAndPlaceShips() {
        System.out.printf("Place %d ships. Provide an X and Y coordinate for each ship.\n", gameboard.getNumberOfShips());
        int count = 0;
        // get and place user ships
        while (count < gameboard.getNumberOfShips() && !quitGame) {
            int X = getUserInput(scanner, "X", count);
            if (quitGame) {
                return;
            }
            int Y = getUserInput(scanner, "Y", count);
            if (quitGame) {
                return;
            }
            if (gameboard.isOnBoard(X, Y) && !gameboard.isShip("user", X, Y)) {
                gameboard.addShip("user", X, Y);
                count++;
            } else {
                if (!gameboard.isOnBoard(X, Y)) {
                    System.out.printf("%d, %d is not on the game board. Please choose another location..\n", X, Y);
                }
                if (gameboard.isShip("user", X, Y)) {
                    System.out.printf("You already have a ship at %d, %d. Please choose another location.\n", X, Y);
                }
            }
        }
        // place computer ships
        System.out.printf("Computer is deploying %d ships... ", gameboard.getNumberOfShips());
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

    private void attackSequence() {
        while (!quitGame && !gameboard.isWon() && !gameboard.isLost()) {
            System.out.println("\nYOUR TURN");
            int X = -1;
            int Y = -1;
            boolean invalidInput = true;
            while (invalidInput) {
                X = getUserInput(scanner, "X");
                if (quitGame) return;
                Y = getUserInput(scanner, "Y");
                if (quitGame) return;
                if (!gameboard.isOnBoard(X, Y)) {
                    System.out.printf("%d, %d is not on the game board. Please choose another location.\n", X, Y);
                    continue;
                }
                if (gameboard.isAlreadyGuessed("user", X, Y)) {
                    System.out.printf("You've already guessed %d, %d. Please choose another location.\n", X, Y);
                    continue;
                }
                invalidInput = false;
            }
            gameboard.addMove("user", X, Y);
            if (gameboard.isShip("computer", X, Y) || gameboard.isShip("user", X, Y)) {
                gameboard.sinkShip(X, Y);
                System.out.printf("Direct hit! Ship at %d, %d is sunk\n", X, Y);
            } else {
                System.out.printf("Nothing at %d, %d! You missed.\n", X, Y);
                gameboard.addPlayerMiss(X, Y);
            }
            if (gameboard.isWon() || gameboard.isLost()) return;
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nCOMPUTER'S TURN");
            invalidInput = true;
            X = -1;
            Y = -1;
            while (invalidInput) {
                Random random = new Random();
                X = random.nextInt(gameboard.getBoardSize());
                Y = random.nextInt(gameboard.getBoardSize());
                if (gameboard.isOnBoard(X, Y) && !gameboard.isAlreadyGuessed("computer", X, Y)) {
                    invalidInput = false;
                    gameboard.addMove("computer", X, Y);
                    if (gameboard.isShip("user", X, Y) || gameboard.isShip("computer", X, Y)) {
                        gameboard.sinkShip(X, Y);
                        System.out.printf("Computer sunk ship at %d, %d!\n", X, Y);
                    } else {
                        System.out.println("Computer missed.");
                    }
                }
            }
            if (gameboard.isWon() || gameboard.isLost()) return;
            System.out.println(gameboard.toString());
            System.out.printf("Your ships remaining: %d | Computer ships remaining: %d\n",
                gameboard.getShipsRemaining("user"), gameboard.getShipsRemaining("computer"));
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void endGame() {
        scanner.close();
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

    private int getUserInput(Scanner scanner, String coordinate) {
        boolean invalidFeedback = true;
        while(invalidFeedback) {
            System.out.printf("Enter %s coordinate to attack: ", coordinate);
            String input = scanner.next().toLowerCase();
            if (input.equals("q")) {
                quitGame = true;
                invalidFeedback = false;
                return -1;
            }
            try {
                int i = Integer.parseInt(input);
                invalidFeedback = false;
                return i;
            } catch ( NumberFormatException e) {
                // while loop will continue in this case
            }
        }
        return -1;
    }

    private int getUserInput(Scanner scanner, String coordinate, int number) {
        boolean invalidFeedback = true;
        while (invalidFeedback) {
            System.out.printf("Enter %s coordinate for ship %d: ", coordinate, number);
            String input = scanner.next().toLowerCase();
            if (input.equals("q")) {
                quitGame = true;
                invalidFeedback = false;
                return -1;
            }
            try {
                int i = Integer.parseInt(input);
                invalidFeedback = false;
                return i;
            } catch ( NumberFormatException e) {
                // while loop will continue in this case
            }
            System.out.println("Invalid coordinate.");
        }

        return -1;
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
