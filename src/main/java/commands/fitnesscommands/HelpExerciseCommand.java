package commands.fitnesscommands;

import commands.Command;
import exceptions.FitnessException;
import exceptions.Wellness360Exception;
import fitness.FitnessMotivator;

public class HelpExerciseCommand implements Command {

    private FitnessMotivator fitnessMotivator;

    public HelpExerciseCommand (FitnessMotivator fitnessMotivator) {
        this.fitnessMotivator = fitnessMotivator;
    }

    @Override
    public void execute() throws FitnessException {
        fitnessMotivator.printHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
