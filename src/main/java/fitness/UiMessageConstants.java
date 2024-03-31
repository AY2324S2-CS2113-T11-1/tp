package fitness;

/**
 * Class that stores static constants of FitnessMotivator Ui messages
 * */
public class UiMessageConstants {

    public static final String NEW_GOAL_MESSAGE = "Lets get working on today's exercises!" +
            System.lineSeparator() + System.lineSeparator();
    public static final String EMPTY_GOAL_MESSAGE = "There are no goals set :(" +
            System.lineSeparator() + System.lineSeparator() +
            "You can set one by doing 'goal new'!";

    public static final String GOAL_MESSAGE = "Here are your goals for today. " +
            "Have you started? Don't be lazy ok?" + System.lineSeparator() + System.lineSeparator();

    public static final String GOAL_STATUS_MESSAGE = "I see there are changes. " +
            "I hope you are making progress..." + System.lineSeparator() + System.lineSeparator();

    public static final String HELP_MESSAGE =
            "Here is a list of possible commands you can use with the Fitness Motivator!" +
                    System.lineSeparator();

    public static final String ADD_EXERCISE_MESSAGE =
            "I have added the following exercise into our list!" + System.lineSeparator();

    public static final String DELETE_EXERCISE_MESSAGE =
            " I have deleted the exercise. Here are the exercises left in the list!" +
            System.lineSeparator();

    public static final String[] HELP_MENU_INSTRUCTIONS = {
        "fitness get: Get 5 random reflection questions",
        "fitness get <exercise_type>: Get a full list of exercises belonging to the exercise type",
        "fitness add <exercise_type>, <exercise_name>, <sets>, <reps>: " +
                "add an exercise to the list of exercises",
        "fitness delete <exercise_type> <index>: Delete the exercise from the list of exercise." +
                "The index is based on the index when you run 'fitness get <exercise_type>",
        "fitness goal: Retrieves the status of all current goals, if it exists",
        "fitness goal new: Overwrites current goals with new set of goals if it exists, " +
                "otherwise creates a brand new set of goals",
        "fitness goal <index>: Toggle the status of the goal",
        "fitness help: Get help menu for reflect commands"
    };
}
