package com.codam.pandemicsim;

public class pandemic {
    public static void main(String[] args) {
        System.out.println("----- Simulation start -----");
        if (args.length < 5) {
            System.out.println("Please provide arguments in order: \n"
                    + "- board size\n" + "- rounds\n"
                    + "- infection threshold\n" + "- recovery threshold\n"
                    + "- coordinates of infected");
            return;
        }
        try {
            Simulation simulation = parseArgs(args);
            validateParsedArgs(simulation);
            simulation.printParams();
            simulation.printBoard(simulation.getBoard(), 0);
            for (int i = 1; i < simulation.getRounds() + 1; i++) {
                simulation.runSimulation();
                simulation.printBoard(simulation.getBoard(), i);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public static Simulation parseArgs(String[] args) {
        Simulation simulation = new Simulation();
        simulation.initBoard(args[0]);
        for (int i = 1; i < args.length; i++) {
            switch (i) {
                case 1:
                    simulation.setRounds(args[i]);
                case 2:
                    simulation.setThresholds(args[i], null);
                case 3:
                    simulation.setThresholds(null, args[i]);
                default:
                    parseCoordinates(simulation, args[i]);
            }
        }
        return simulation;
    }

    public static void parseCoordinates(Simulation simulation, String arg) {
        if (arg.matches(".*[<>,].*")) {
            String arg_conv = arg.replaceAll("[^0-9,]", "");
            String[] arg_arr = arg_conv.split(",");
            for (int j = 0; j < arg_arr.length - 1; j += 2) {
                simulation.markInfected(arg_arr[j], arg_arr[j + 1]);
            }
            simulation.setStartingInfections(arg_arr.length / 2);
        }
    }

    public static void validateParsedArgs(Simulation simulation)
            throws Simulation.badArgsException {
        if (simulation.getSize() < 1
                || simulation.getRounds() < 1
                || simulation.getInfectionThreshold() < 1
                || simulation.getRecoveryThreshold() < 1
                || simulation.getBoard() == null
                || simulation.getStartingInfections() < 1) {
            throw new Simulation.badArgsException(
                    "Cannot run simulation based on provided arguments");
        }
    }
}
