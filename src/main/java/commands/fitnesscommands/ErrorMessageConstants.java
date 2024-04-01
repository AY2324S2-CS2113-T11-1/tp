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
            "Forgetting something? Key in the correct parameters please!" +
            System.lineSeparator() + System.lineSeparator() +
            "Example: fitness delete <type> <index>";

    public static final String DELETE_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE =
            "Please enter an index within bounds!" + System.lineSeparator() +
            "You can do 'fitness get <type> to see the indexes of the exercises!";

    public static final String ILLEGAL_TYPE_ERROR_MESSAGE = "Hmm...Invalid type of exercise..." +
            System.lineSeparator() + "Only the following exercise types are allowed: " +
            "Arms, Chest, Abs, Back and Legs!";

    public static final String INCORRECT_INTEGER_ERROR_MESSAGE =
            "Did you enter your Sets and Reps correctly? :(";

    public static final String ILLEGAL_GOAL_PARAMS_ERROR_MESSAGE =
            "Are you trying to create a new goal? You can try 'fitness goal new'!" +
            System.lineSeparator() +
            "You can also do 'fitness goal <index>' to mark and unmark exercise goals!" +
            System.lineSeparator() +
            "Do 'fitness goal' to check the index of each exercise goal.";
}
