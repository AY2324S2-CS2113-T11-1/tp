package commands.fitnesscommands;

import commands.Command;
import exceptions.FitnessException;
import exceptions.Wellness360Exception;
import fitness.FitnessMotivator;

public class GoalExerciseCommand implements Command {

    private FitnessMotivator fitnessMotivator;
    private String commandArgs;

    public GoalExerciseCommand(FitnessMotivator fitnessMotivator, String commandArgs) {
        this.fitnessMotivator = fitnessMotivator;
        this.commandArgs = commandArgs;
    }

    private String checkCommandArgs(String commandArgs) throws FitnessException {
        if (commandArgs.isBlank() || commandArgs.isEmpty()) {
            return null;
        }
        commandArgs = commandArgs.trim();
        if (commandArgs.equalsIgnoreCase("new")) {
            return "new";
        }
        if (commandArgs.matches("^[1-5]$")) {
            return commandArgs;
        }
        String errorMessage = "Are you trying to create a new goal? You can try 'goal new'!" +
                System.lineSeparator() +
                "You can also do 'goal <index>' to mark and unmark exercises!";
        throw new FitnessException(errorMessage);
    }

    @Override
    public void execute() throws Wellness360Exception {
        String parsedCommand = checkCommandArgs(commandArgs);
        if (parsedCommand == null) {
            fitnessMotivator.goalStatus();
        } else {
            if (parsedCommand.contentEquals("new")) {
                fitnessMotivator.newGoals();
            }
            if (parsedCommand.matches("^[1-5]$")) {
                int exerciseIndex = Integer.parseInt(parsedCommand);
                fitnessMotivator.toggleGoal(exerciseIndex);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
