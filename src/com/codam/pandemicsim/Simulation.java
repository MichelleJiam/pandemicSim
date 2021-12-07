package com.codam.pandemicsim;
import java.lang.*;

public class Simulation {
    private int     size = 0;
    private int     rounds = 0;
    private int     th_infection = 0;
    private int     th_recovery = 0;
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
            th_infection = tryParse(arg_infection, "infection threshold");
        if (arg_recovery != null)
            th_recovery = tryParse(arg_recovery, "recovery threshold");
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
        return th_infection;
    }

    int    getRecoveryThreshold() {
        return th_recovery;
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
//        System.out.println("markInfected: x" + coord_x + " y " + coord_y);
//        System.out.println(board[row][col]);
        board[row][col] = 1;
//        System.out.println(board[row][col]);
    }

    void    runSimulation() {
        int[][] newBoard = copyBoard();
//        printBoard(newBoard, 0);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int adjInfected = sumAdjacent(row, col);
//                System.out.println("[" + (col+1) + "," + (row+1) + "]: " + adjInfected);
                if (board[row][col] == 0 && adjInfected > th_infection
                    || board[row][col] == 1 && adjInfected > th_recovery) {
//                    System.out.println("[" + (col + 1) + "," + (row + 1) + "] hit threshold");
                    newBoard[row][col] ^= 1;
                }
            }
        }
        board = newBoard;
    }

    void    printParams() {
        System.out.println("Size: " + size);
        System.out.println("Rounds: " + rounds);
        System.out.println("Infection threshold: " + th_infection);
        System.out.println("Recovery threshold: " + th_recovery);
        System.out.println("Infected persons at start: " + startingInfections);
    }

    void    printBoard(int[][] boardToPrint, int round) {
        System.out.println("\nCurrent state of board [round " + round + "]:");
        System.out.print("  ");
        for (int i = 1; i < size + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
        for (int row = 0; row < size; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < size; col++) {
                System.out.print(redCode(boardToPrint[row][col]) + boardToPrint[row][col] + " ");
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

    private int sumAdjacent(int x, int y) {
        int sum = 0;
        for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, size - 1); i++) {
            for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, size - 1); j++) {
                if (i != x || j != y) {
                    sum += board[i][j];
                }
            }
        }
        return sum;
    }

    private final String redCode(int on) {
        final String RED = "\u001B[31m";
        final String RESET = "\033[0m";

        return (on == 1 ? RED : RESET);
    }
}
