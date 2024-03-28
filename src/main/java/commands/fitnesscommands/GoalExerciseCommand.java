package commands.fitnesscommands;

import commands.Command;
import exceptions.Wellness360Exception;
import fitness.FitnessMotivator;

public class GoalExerciseCommand implements Command {

    private FitnessMotivator fitnessMotivator;
    private String commandArgs;

    public GoalExerciseCommand(FitnessMotivator fitnessMotivator, String commandArgs) {
        this.fitnessMotivator = fitnessMotivator;
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute() throws Wellness360Exception {
        if (commandArgs.isBlank() || commandArgs.isEmpty()) {
            fitnessMotivator.goalStatus();
        }
        commandArgs = commandArgs.trim();
        if (commandArgs.equalsIgnoreCase("new")) {
            fitnessMotivator.newGoal();
        }
        if (commandArgs.matches("^[1-5]$")) {
            fitnessMotivator.toggleGoal();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
