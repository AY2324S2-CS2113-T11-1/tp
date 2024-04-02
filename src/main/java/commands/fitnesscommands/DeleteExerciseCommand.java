package commands.fitnesscommands;

import commands.Command;
import exceptions.FitnessException;
import fitness.FitnessMotivator;
import fitness.ExerciseType;

import static commands.fitnesscommands.ErrorMessageConstants.INSUFFICIENT_DELETE_PARAMS_ERROR_MESSAGE;
import static commands.fitnesscommands.ErrorMessageConstants.INCORRECT_INTEGER_ERROR_MESSAGE;
import static commands.fitnesscommands.ErrorMessageConstants.DELETE_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE;

public class DeleteExerciseCommand implements Command {

    public static final int REQUIRED_DELETE_PARAMS = 2;
    private FitnessMotivator fitnessMotivator;
    private String[] commandArgs;

    public DeleteExerciseCommand(FitnessMotivator fitnessMotivator, String commandArgs)
            throws FitnessException {
        this.fitnessMotivator = fitnessMotivator;
        this.commandArgs = checkCommandArgs(commandArgs);
    }

    /**
     * Validates the command argument given for the fitness delete command.
     *
     * @param commandArgs A string of arguments
     * @return A split array of strings of size 2 if there are no issues found with the string
     *         input
     *
     * @throws FitnessException Thrown when improper command arguments are found
     * */
    private String[] checkCommandArgs(String commandArgs) throws FitnessException {
        String[] tempCommandArgs = commandArgs.split(" ", 2);

        // Handles insufficient parameters entered
        if (tempCommandArgs.length != REQUIRED_DELETE_PARAMS) {
            throw new FitnessException(INSUFFICIENT_DELETE_PARAMS_ERROR_MESSAGE);
        }

        // String Cleaning
        tempCommandArgs[0] = tempCommandArgs[0].trim().toUpperCase();
        tempCommandArgs[1] = tempCommandArgs[1].trim();
        ExerciseType type = ExerciseType.valueOf(tempCommandArgs[0]);

        int maxIndex = fitnessMotivator.allExercises.size(type);

        // Handles the case where non-integer values are entered in parameters
        if (!tempCommandArgs[1].matches("\\d+")) {
            throw new FitnessException(INCORRECT_INTEGER_ERROR_MESSAGE);
        }

        // Handles the case where the integer entered is out of bounds
        if (Integer.parseInt(tempCommandArgs[1]) > maxIndex) {
            throw new FitnessException(DELETE_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE);
        }

        return tempCommandArgs;
    }

    @Override
    public void execute() throws FitnessException {
        fitnessMotivator.deleteExercise(commandArgs);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
