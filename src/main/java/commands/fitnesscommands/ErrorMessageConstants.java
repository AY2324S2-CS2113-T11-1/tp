package commands.fitnesscommands;

/**
 * Class that stores static constants of error messages
 * */
public class ErrorMessageConstants {
    public static final String INSUFFICIENT_PARAMS_ERROR_MESSAGE =
            "Forgetting something? Key in the correct parameters please!" +
            System.lineSeparator() + System.lineSeparator() +
            "Example: fitness add <exercise_type>, <exercise_name>, <sets>, <reps>";

    public static final String ILLEGAL_TYPE_ERROR_MESSAGE = "Hmm...Invalid type of exercise..." +
            System.lineSeparator() + "Only the following exercise types are allowed: " +
            "Arms, Chest, Abs, Back and Legs!";

    public static final String INCORRECT_INTEGER_ERROR_MESSAGE =
            "Did you enter your Sets and Reps correctly? :(";

    public static final String ILLEGAL_GOAL_PARAMS_ERROR_MESSAGE =
            "Are you trying to create a new goal? You can try 'goal new'!" +
            System.lineSeparator() +
            "You can also do 'goal <index>' to mark and unmark exercises!";
}
