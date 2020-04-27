import java.util.Scanner;
import java.util.Random;

public class Game {
    private static final int NUMBER_OF_SHIPS = 2;
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
                    Thread.sleep(1800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(gameboard.toString());
                System.out.println("\nThe seas are empty!");
                break;
            } else {
                System.out.println("Try again: 'S' to start a new game or 'Q' to quit");
            }
        } while(!quitGame);
        if (!quitGame) {getAndPlaceShips();}
        if (!quitGame) {System.out.println(gameboard.toString());}

        if (quitGame || gameboard.isWon() || gameboard.isLost()) {
            endGame();
        }
    }

    private void getAndPlaceShips() {
        System.out.println("Place your ships on the sea. Provide an X and Y coordinate for each ship.");
        int count = 0;
        // get and place user ships
        while (count < NUMBER_OF_SHIPS && !quitGame) {
            int X = getUserInput(scanner, "X", count);
            if (quitGame) {
                return;
            }
            int Y = getUserInput(scanner, "Y", count);
            if (quitGame) {
                return;
            }
            if (gameboard.isOnBoard(X, Y) && !gameboard.isAShip("user", X, Y)) {
                gameboard.addShip("user", X, Y);
                count++;
            } else {
                if (!gameboard.isOnBoard(X, Y)) {
                    System.out.printf("%d, %d is not on the game board. Please choose another location..\n", X, Y);
                }
                if (gameboard.isAShip("user", X, Y)) {
                    System.out.printf("You already have a ship at %d, %d. Please choose another location.\n", X, Y);
                }
            }
        }
        // place computer ships
        System.out.printf("Computer is deploying %d ships... ", NUMBER_OF_SHIPS);
        int computerShipCount = 0;
        while (computerShipCount < NUMBER_OF_SHIPS) {
            Random random = new Random();
            int X = random.nextInt(gameboard.getBoardsize());
            int Y = random.nextInt(gameboard.getBoardsize());
            if (gameboard.isOnBoard(X, Y) && !gameboard.isAShip("computer", X, Y) && !gameboard.isAShip("user", X, Y)) {
                gameboard.addShip("computer", X, Y);
                computerShipCount++;
            }
        }
        System.out.println("Computer's ships are deployed.");
    }

    private void attackSequence() {
        while (!quitGame && !gameboard.isWon() && !gameboard.isLost()) {
            // get user input which is valid guess or 'Q'
            // update board
            // check if won/lost
            // get computer guess
            // update board
        }
    }

    private void endGame() {
        scanner.close();
        if (quitGame) {
            System.out.println("Quitting game...\nThanks for playing! Goodbye.");
        }
    }

    private int getUserInput(Scanner scanner, String coordinate) {
        boolean invalidFeedback = true;
        String input = scanner.next().toLowerCase();
        while(invalidFeedback) {
            System.out.printf("Enter %s coordinate to attack: ", coordinate);
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
}
