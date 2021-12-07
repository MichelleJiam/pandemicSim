package com.codam.pandemicsim;
import java.util.ArrayList;

public class pandemic {
    public static void main(String[] args) {
	    System.out.println("Hello World");
        try {
            Simulation simulation = parseArgs(args);
            simulation.printParams();
            simulation.printBoard();
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
    public static Simulation parseArgs(String[] args) {
        Simulation  simulation = new Simulation();
        simulation.initBoard(args[0]);
        for (int i = 1; i < args.length; i++) {
            System.out.println(args[i]);
            if (args[i].matches(".*[<>,].*")) {
                String arg_conv = args[i].replaceAll("[^0-9,]", "");
                String[] arg_arr = arg_conv.split(",");
                for (int j = 0; j < arg_arr.length; j += 2) {
                    System.out.println("j is " + String.valueOf(j));
                    System.out.println(arg_arr[j] + "," + arg_arr[j + 1]);
                    simulation.markInfected(arg_arr[j], arg_arr[j + 1]);
                }
                //                int j = 0;
//                for (String p: arg_conv.split(",")) {
//                    System.out.println(p);
////                    params[i][j] = Integer.parseInt(p));
////                    j++;
//                }
            } else {
                if (i == 1) {
                    simulation.setRounds(args[i]);
                } else if (i == 2 || i == 3) {
                    simulation.setThresholds(args[i]);
                }
            }
        }
        return simulation;
    }
}
