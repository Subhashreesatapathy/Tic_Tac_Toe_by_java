import java.util.Scanner;

class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        initializeBoard(board);

        char player = 'X';
        boolean gameOver = false;
        int moves = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Players take turns entering row and column numbers (0-2) to place their mark.\n");

        while (!gameOver) {
            printBoard(board);
            int row, col;

            while (true) {
                System.out.print("Player " + player + ", enter your move (row and column, space-separated): ");
                if (scanner.hasNextInt()) {
                    row = scanner.nextInt();
                    if (scanner.hasNextInt()) {
                        col = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (isValidMove(board, row, col)) {
                            break;
                        } else {
                            System.out.println("Invalid move! That spot is taken or out of bounds. Try again.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter two numbers (0-2).");
                        scanner.nextLine(); // Consume invalid input
                    }
                } else {
                    System.out.println("Invalid input! Please enter numbers only.");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            board[row][col] = player;
            moves++;

            if (haveWon(board, player)) {
                printBoard(board);
                System.out.println("Player " + player + " wins!");
                gameOver = true;
            } else if (moves == 9) {
                printBoard(board);
                System.out.println("It's a tie! No more moves left.");
                gameOver = true;
            } else {
                player = (player == 'X') ? 'O' : 'X'; // Switch player
            }
        }

        System.out.println("Game over! Thanks for playing.");
        scanner.close();
    }

    // Initialize the board with empty spaces
    public static void initializeBoard(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    // Check if a move is valid
    public static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    // Check if the current player has won
    public static boolean haveWon(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || // Rows
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) { // Columns
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) || // Diagonal 1
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);   // Diagonal 2
    }

    // Display the game board with row and column numbers
    public static void printBoard(char[][] board) {
        System.out.println("\n  0   1   2 ");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if (col < 2) System.out.print(" | ");
            }
            System.out.println();
            if (row < 2) System.out.println("  ---------");
        }
        System.out.println();
    }
}
