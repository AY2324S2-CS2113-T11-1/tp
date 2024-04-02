package commands.fitnesscommands;

import commands.Command;
import exceptions.FitnessException;
import fitness.ExerciseType;
import fitness.FitnessMotivator;

import static commands.fitnesscommands.ErrorMessageConstants.ILLEGAL_TYPE_ERROR_MESSAGE;
import static commands.fitnesscommands.ErrorMessageConstants.INCORRECT_SETS_REPS_ERROR_MESSAGE;
import static commands.fitnesscommands.ErrorMessageConstants.INSUFFICIENT_ADD_PARAMS_ERROR_MESSAGE;
import static fitness.FitnessMotivator.REQUIRED_NUM_OF_PARAMETERS;

public class AddExerciseCommand implements Command {

    private FitnessMotivator fitnessMotivator;
    private String[] commandArgs;

    public AddExerciseCommand(FitnessMotivator fitnessMotivator, String commandArgs)
            throws FitnessException {
        this.fitnessMotivator = fitnessMotivator;
        this.commandArgs = checkCommandArgs(commandArgs);
    }

    /**
     * Validates the command argument given for the fitness add command.
     *
     * @param commandArgs A string of arguments
     * @return A split array of strings of size 4 if there are no issues found with the string
     *         input
     *
     * @throws FitnessException Thrown when improper command arguments are found
     * */
    private String[] checkCommandArgs(String commandArgs) throws FitnessException {
        String[] tempCommandArgs = commandArgs.split(",", 4);

        // Handles insufficient parameters entered
        if (tempCommandArgs.length != REQUIRED_NUM_OF_PARAMETERS) {
            throw new FitnessException(INSUFFICIENT_ADD_PARAMS_ERROR_MESSAGE);
        }

        // String Cleaning
        tempCommandArgs[0] = tempCommandArgs[0].trim();
        tempCommandArgs[1] = tempCommandArgs[1].trim();
        tempCommandArgs[2] = tempCommandArgs[2].trim();
        tempCommandArgs[3] = tempCommandArgs[3].trim();

        // Handles the case where non-integer values are entered in parameters that should only
        // be integers
        if (!tempCommandArgs[2].matches("\\d+") ||
                !tempCommandArgs[3].matches("\\d+")) {
            throw new FitnessException(INCORRECT_SETS_REPS_ERROR_MESSAGE);
        }

        // Checks that the entered type belongs to one of the ExerciseType Enum
        try {
            String exerciseTypeString = tempCommandArgs[0].toUpperCase().trim();
            ExerciseType.valueOf(exerciseTypeString);
        } catch (IllegalArgumentException e) {
            throw new FitnessException(ILLEGAL_TYPE_ERROR_MESSAGE);
        }
        return tempCommandArgs;
    }

    @Override
    public void execute() throws FitnessException {
        fitnessMotivator.addExercises(commandArgs);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
