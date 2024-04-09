package commands.fitnesscommands;

/**
 * Class that stores static constants of error messages
 * */
public class ErrorMessageConstants {
    public static final String INSUFFICIENT_ADD_PARAMS_ERROR_MESSAGE =
            "Forgetting something? Key in the correct parameters please!" +
            System.lineSeparator() + System.lineSeparator() +
            "Example: fitness add <exercise_type>, <exercise_name>, <sets>, <reps>";

    public static final String INSUFFICIENT_DELETE_PARAMS_ERROR_MESSAGE =
            "Key in the correct parameters please!" +
            System.lineSeparator() + System.lineSeparator() +
            "Example: fitness delete <type> <index>";

    public static final String ILLEGAL_TYPE_ERROR_MESSAGE = "Hmm...Invalid type of exercise..." +
            System.lineSeparator() + "Only the following exercise types are allowed: " +
            "Arms, Chest, Abs, Back and Legs!";

    public static final String INCORRECT_SETS_REPS_ERROR_MESSAGE =
            "Did you enter your Sets and Reps correctly? :(";

    public static final String ADDITIONAL_PARAMS_ERROR_MESSAGE =
            "Additional parameters detected. Don't be extra please.";

    public static final String INVALID_DELETE_INDEX_ERROR_MESSAGE =
            "Please use a valid delete index. " +
            "Use 'fitness get [Exercise_Type]' to get the indexes!";

    public static final String ILLEGAL_GOAL_PARAMS_ERROR_MESSAGE =
            "Are you trying to create a new goal? You can try 'fitness goal new'!" +
            System.lineSeparator() +
            "You can also do 'fitness goal <index>' to mark and unmark exercise goals!" +
            System.lineSeparator() +
            "Do 'fitness goal' to check the index of each exercise goal.";

    public static final String COMMAND_SUGGESTION_MESSAGE =
            "Unknown command. Did you try any of the following command types?"
            + System.lineSeparator() +
            "- 'fitness get'" + System.lineSeparator() +
            "- 'fitness add'" + System.lineSeparator() +
            "- 'fitness goal'" + System.lineSeparator() +
            "- 'fitness delete'" + System.lineSeparator() +
            "Do 'fitness help' to see what are the exact commands and " +
                "parameters which are accepted!";
}
