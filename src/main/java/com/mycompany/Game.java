import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int player1Wins, player1Losses, player1Draws;
    private int player2Wins, player2Losses, player2Draws;
    private Scanner scanner;
    private boolean gameRunning;

    public Game() {
        board = new Board();
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = getRandomFirstPlayer(player1, player2);
        player1Wins = player1Losses = player1Draws = 0;
        player2Wins = player2Losses = player2Draws = 0;
        scanner = new Scanner(System.in);
        gameRunning = true;
    }

    private void updateScores(Player winner) {
        if (winner == player1) {
            player1Wins++;
            player2Losses++;
        } else if (winner == player2) {
            player2Wins++;
            player1Losses++;
        } else {
            player1Draws++;
            player2Draws++;
        }
    }

    private void printScores() {
        System.out.println("Player X: Wins: " + player1Wins + ", Losses: " + player1Losses + ", Draws: " + player1Draws);
        System.out.println("Player O: Wins: " + player2Wins + ", Losses: " + player2Losses + ", Draws: " + player2Draws);
    }

    private void swapPlayer() {
        Player temp = currentPlayer;
        if(temp == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
        // player1 = player2;
        // player2 = temp;
        // currentPlayer = player1;
    }

    private Player getRandomFirstPlayer(Player player1, Player player2){
        Random random = new Random();
        return random.nextBoolean() ? player1 : player2;
    }

    public void start() {
        while (gameRunning) {
            board.printBoard();
            System.out.println("Player " + currentPlayer.getSymbol() + ", enter your move (row and column) or 'q' to quit:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting the game...");
                gameRunning = false;
                continue;
            }

            String[] move = input.split(" ");
            if (move.length != 2) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(move[0]);
                col = Integer.parseInt(move[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            if (board.placeMark(row - 1, col - 1, currentPlayer.getSymbol())) {
                boolean gameWon = board.checkWin(currentPlayer.getSymbol());
                if (gameWon) {
                    board.printBoard();
                    System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                    updateScores(currentPlayer);
                    printScores();
                    board.initializeBoard();
                    swapPlayer();
                } else if (board.isFull()) {
                    board.printBoard();
                    System.out.println("The game is a tie!");
                    updateScores(currentPlayer);
                    printScores();
                    board.initializeBoard();
                    swapPlayer();
                } else {
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                }
            } else {
                System.out.println("This move is not valid");
            }
        }

        scanner.close();
    }
}
