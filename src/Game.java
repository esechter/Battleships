import java.util.Scanner;

public class Game {
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
                scanner.close();
                break;
            } else {
                System.out.println("Try again: 'S' to start a new game or 'Q' to quit");
            }
        } while(!quitGame);
        if (quitGame) {
            endGame();
        }
    }

    public void endGame() {
        if (quitGame) {
            scanner.close();
            System.out.println("Quitting game...\nThanks for playing! Goodbye.");
        }
    }
}
