package parser;

import commands.Command;
import commands.fitnesscommands.AddExerciseCommand;
import commands.fitnesscommands.GetExercisesCommand;
import commands.fitnesscommands.GoalExerciseCommand;
import commands.fitnesscommands.HelpExerciseCommand;
import commands.fitnesscommands.DeleteExerciseCommand;
import exceptions.FitnessException;
import fitness.FitnessMotivator;

import static commands.fitnesscommands.ErrorMessageConstants.COMMAND_SUGGESTION_MESSAGE;

public class FitnessCommandParser {

    private static final int COMMAND_LENGTH = 2;
    public static Command determineFitnessCommand(FitnessMotivator fitnessMotivator, String commandArgs)
            throws FitnessException {

        String[] userCommand = commandArgs.trim().split("\\s+", 2);
        String userFitnessCommand = userCommand[0].trim();

        String fitnessCommandArgs =
            userCommand.length == COMMAND_LENGTH ? userCommand[1].trim() : "";

        switch (userFitnessCommand) {
        case "get":
            return new GetExercisesCommand(fitnessMotivator, fitnessCommandArgs);
        case "add":
            return new AddExerciseCommand(fitnessMotivator, fitnessCommandArgs);
        case "goal":
            return new GoalExerciseCommand(fitnessMotivator, fitnessCommandArgs);
        case "help":
            return new HelpExerciseCommand(fitnessMotivator, fitnessCommandArgs);
        case "delete":
            return new DeleteExerciseCommand(fitnessMotivator, fitnessCommandArgs);
        default:
            throw new FitnessException(COMMAND_SUGGESTION_MESSAGE);
        }
    }
}
