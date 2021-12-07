package com.codam.pandemicsim;

// TODO: reduce access to attributes with private
public class Simulation {
    int size = 0;
    int rounds = 0;
    int th_infection = 0;
    int th_recovery = 0;
    char[][] board;

    void    initBoard(String arg) {
        setSize(arg);
        board = new char[size][size];

        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                board[row][col] = '.';
    }
    void    setSize(String arg) {
        size = tryParse(arg, "size");
    }
    void    setRounds(String arg) {
        rounds = tryParse(arg, "rounds");
    }
    void    setThresholds(String arg) {
        if (th_infection == 0) {
            th_infection = tryParse(arg, "infection threshold");
        } else {
            th_recovery = tryParse(arg, "recovery threshold");
        }
    }
    void    markInfected(String coord_x, String coord_y) {
        int col = tryParse(coord_x, "infected coordinate");
        int row = tryParse(coord_y, "infected coordinate");
        System.out.println("markInfected: x" + coord_x + " y " + coord_y);
        board[row][col] = 'i';
//        System.out.println(board[row][col]);
    }
    void    printParams() {
        System.out.println("Size: " + size);
        System.out.println("Rounds: " + rounds);
        System.out.println("Infection threshold: " + th_infection);
        System.out.println("Recovery threshold: " + th_recovery);
    }
    void    printBoard() {
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                System.out.println(board[row][col]);
    }
    private int tryParse(String arg, String argType) {
        int n = 0;
        try {
            n = Integer.parseInt(arg);
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + argType + " argument");
        }
        return n;
    }
}
