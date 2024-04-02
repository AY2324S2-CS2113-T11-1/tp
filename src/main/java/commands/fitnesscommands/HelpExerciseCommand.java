package commands.fitnesscommands;

import commands.Command;
import exceptions.FitnessException;
import fitness.FitnessMotivator;

import static commands.fitnesscommands.ErrorMessageConstants.ADDITIONAL_PARAMS_ERROR_MESSAGE;

public class HelpExerciseCommand implements Command {

    private FitnessMotivator fitnessMotivator;

    public HelpExerciseCommand (FitnessMotivator fitnessMotivator, String commandArgs)
            throws FitnessException {
        this.fitnessMotivator = fitnessMotivator;
        checkCommandArgs(commandArgs);
    }

    private void checkCommandArgs(String commandArgs) throws FitnessException {
        if (!commandArgs.isBlank()) {
            throw new FitnessException(ADDITIONAL_PARAMS_ERROR_MESSAGE);
        }
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
