public class Board {
    private char[][] board;
    private static final int SIZE = 3;

    //constructor 
    public Board() {
        board = new char[SIZE][SIZE] ;
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    //print board
    public void printBoard() {   
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //check (draw case)
    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }


    //check the symbol that have another symbol in this posiotion already?
    public boolean placeMark(int row, int col, char symbol) {
        if (row >= 0 && col >= 0 && row < SIZE && col < SIZE && board[row][col] == '-') {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

        //checkwin
    public boolean checkWin(char symbol) {
        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }

        return false;
    }

}
