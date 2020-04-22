import java.util.Scanner;

public class Battleship {
    private static boolean quitGame = false;
    private static Scanner scanner;
    private static Gameboard game;

    public static void main(String[] args) {
        initializeGame();
        scanner = new Scanner(System.in);
        do {
            String input = scanner.next().toLowerCase();
            if (input.equals("q")) {
                quitGame = true;
            } else if (input.equals("s")) {
                System.out.println("Starting game");
                break;
            } else {
                System.out.println("Try again, 'S' or 'Q'");
            }
        } while(!quitGame);
        if (quitGame) {
            endGame();
        }
        scanner.close();
    }

    public static void initializeGame() {
        game = new Gameboard();
        System.out.println("*** Welcome to Battleships Game! ***\n");
        System.out.println("Enter 'S' to start game, type 'Q' any time to quit game.");
    }

    public static void endGame() {
        if (quitGame) {
            scanner.close();
            System.out.println("Quitting game...\nThanks for playing! Goodbye.");
        }
    }
}
