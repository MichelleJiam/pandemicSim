package com.codam.pandemicsim;
// TODO: add currentRound variable to pass to printer
public class Simulation {
    private int     size = 0;
    private int     rounds = 0;
    private int     thrInfection = 0;
    private int     thrRecovery = 0;
    private int[][] board = null;
    private int     startingInfections = 0;

    public static class badArgsException extends Exception {
        public badArgsException(String message) {
            super(message);
        }
    }

    void    initBoard(String arg) {
        setSize(arg);
        board = new int[size][size];

        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                board[row][col] = 0;
    }

    void    setSize(String arg) {
        size = tryParse(arg, "size");
    }

    void    setRounds(String arg) {
        rounds = tryParse(arg, "rounds");
    }

    void    setThresholds(String arg_infection, String arg_recovery) {
        if (arg_infection != null)
            thrInfection = tryParse(arg_infection, "infection threshold");
        if (arg_recovery != null)
            thrRecovery = tryParse(arg_recovery, "recovery threshold");
    }

    void    setStartingInfections(int n) {
        startingInfections = n;
    }

    int    getSize() {
        return size;
    }

    int    getRounds() {
        return rounds;
    }

    int    getInfectionThreshold() {
        return thrInfection;
    }

    int    getRecoveryThreshold() {
        return thrRecovery;
    }

    int[][] getBoard() {
        return board;
    }

    int     getStartingInfections() {
        return startingInfections;
    }

    void    markInfected(String coord_x, String coord_y) {
        int col = tryParse(coord_x, "infected coordinate") - 1;
        int row = tryParse(coord_y, "infected coordinate") - 1;
        board[row][col] = 1;
    }

    void    runSimulation() {
        int[][] newBoard = copyBoard();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int adjInfected = sumAdjacent(row, col);
                if (board[row][col] == 0 && adjInfected > thrInfection
                        || board[row][col] == 1 && adjInfected > thrRecovery) {
                    newBoard[row][col] ^= 1;
                }
            }
        }
        board = newBoard;
    }

    void    printParams() {
        System.out.println("Board size: " + size);
        System.out.println("Rounds: " + rounds);
        System.out.println("Infection threshold: " + thrInfection);
        System.out.println("Recovery threshold: " + thrRecovery);
        System.out.println("Infected persons at start: " + startingInfections);
    }

    void    printBoard(int[][] boardToPrint, int round) {
        System.out.println("\nCurrent state of board [round " + round + "]:");
        System.out.print(String.format("%3c", ' '));
        for (int i = 1; i < size + 1; i++) {
            System.out.print(String.format("%3d", i));
        }
        System.out.print("\n");
        for (int row = 0; row < size; row++) {
            System.out.print(String.format("%3d", row + 1));
            for (int col = 0; col < size; col++) {
                System.out.print(redCode(boardToPrint[row][col])
                        + String.format("%3d", boardToPrint[row][col]));
            }
            System.out.print(redCode(0) + "\n");
        }
    }

    private int tryParse(String arg, String argType) throws NumberFormatException {
        int n = 0;
        try {
            n = Integer.parseInt(arg);
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + argType + " argument");
        }
        return n;
    }

    private int[][] copyBoard() {
        int[][] newBoard = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        return newBoard;
    }

    private int sumAdjacent(int row, int col) {
        int sum = 0;
        for (int r = Math.max(row - 1, 0); r <= Math.min(row + 1, size - 1); r++) {
            for (int c = Math.max(col - 1, 0); c <= Math.min(col + 1, size - 1); c++) {
                if (r != row || c != col) {
                    sum += board[r][c];
                }
            }
        }
        return sum;
    }

    private String redCode(int on) {
        final String RED = "\u001B[31m";
        final String RESET = "\033[0m";

        return (on == 1 ? RED : RESET);
    }
}
