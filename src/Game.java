import java.util.Scanner;

public class Game {
    private static final int NUMBEROFSHIPS = 5;
    private boolean quitGame = false;
    private Scanner scanner;
    private Gameboard gameboard;

    public void startGame() {
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
                    Thread.sleep(2000);
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

        if (quitGame || gameboard.isWon() || gameboard.isLost()) {
            endGame();
        }
    }

    private void getAndPlaceShips() {
        System.out.println("Place your ships on the sea. Provide an X and Y coordinate for each ship.");
        int count = 0;
        while (count < NUMBEROFSHIPS && !quitGame) {
            System.out.printf("Enter X coordinate for ship %d: ", count + 1);
            if (scanner.hasNextInt()) {
                int X = scanner.nextInt();
                System.out.printf("Enter Y coordinate for ship %d: ", count + 1);
                if (scanner.hasNextInt()) {
                    int Y = scanner.nextInt();
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
                } else if (scanner.next().toLowerCase().equals("q")) {
                    quitGame = true;
                    return;
                } else {
                System.out.println("Bad input, please try again");
                }
            } else if (scanner.next().toLowerCase().equals("q")) {
                quitGame = true;
                return;
            } else {
                System.out.println("Bad input, please try again");
            }
        }
    }

    private void endGame() {
        scanner.close();
        if (quitGame) {
            System.out.println("Quitting game...\nThanks for playing! Goodbye.");
        }
    }
}
