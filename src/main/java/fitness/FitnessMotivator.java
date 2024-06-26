package fitness;

import fitness.exercise.Exercise;
import fitness.exercise.ExerciseList;
import fitness.goals.ExerciseGoalList;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static commands.fitnesscommands.DeleteExerciseCommand.REQUIRED_DELETE_PARAMS;
import static fitness.UiMessageConstants.ADD_EXERCISE_MESSAGE;
import static fitness.UiMessageConstants.EMPTY_GOAL_MESSAGE;
import static fitness.UiMessageConstants.GOAL_MESSAGE;
import static fitness.UiMessageConstants.NEW_GOAL_MESSAGE;
import static fitness.UiMessageConstants.GOAL_STATUS_MESSAGE;
import static fitness.UiMessageConstants.HELP_MESSAGE;
import static fitness.UiMessageConstants.DELETE_EXERCISE_MESSAGE;
import static fitness.UiMessageConstants.MIN_EXERCISES_MESSAGE;
import static fitness.UiMessageConstants.HELP_MENU_INSTRUCTIONS;


/**
 * Contains methods that execute given the respective commands
 * */
public class FitnessMotivator {

    public static final String DATA_FILE_PATH = "./data/exerciselist.txt";
    public static final String GOALS_FILE_PATH = "./data/exercisegoallist.txt";

    // Required Number of parameters for the fitness add command
    public static final int REQUIRED_NUM_OF_PARAMETERS = 4;

    public ExerciseList allExercises = new ExerciseList();
    public ExerciseGoalList dailyGoals = new ExerciseGoalList();

    public FitnessMotivator() {}

    private Exercise[] fiveRandomExercises() {
        Random random = new Random();
        int randomInt1 = random.nextInt(allExercises.size(ExerciseType.ARMS));
        int randomInt2 = random.nextInt(allExercises.size(ExerciseType.CHEST));
        int randomInt3 = random.nextInt(allExercises.size(ExerciseType.ABS));
        int randomInt4 = random.nextInt(allExercises.size(ExerciseType.BACK));
        int randomInt5 = random.nextInt(allExercises.size(ExerciseType.LEGS));

        Exercise exercise1 = allExercises.get(ExerciseType.ARMS, randomInt1);
        Exercise exercise2 = allExercises.get(ExerciseType.CHEST, randomInt2);
        Exercise exercise3 = allExercises.get(ExerciseType.ABS, randomInt3);
        Exercise exercise4 = allExercises.get(ExerciseType.BACK, randomInt4);
        Exercise exercise5 = allExercises.get(ExerciseType.LEGS, randomInt5);

        return new Exercise[]{exercise1, exercise2, exercise3, exercise4, exercise5};
    }

    /**
     * Gets one randomised exercise per type, then prints it to the UI.
     *
     * @return A string that lists 5 exercises of different type
     * */
    public String getExercises() {
        Exercise[] exercises = fiveRandomExercises();

        String message = "These are some of the exercises you can do! " +
                "LETS GET STRONK MY G" + System.lineSeparator() + System.lineSeparator() +
                "1. " + exercises[0] + System.lineSeparator() +
                "2. " + exercises[1] + System.lineSeparator() +
                "3. " + exercises[2] + System.lineSeparator() +
                "4. " + exercises[3] + System.lineSeparator() +
                "5. " + exercises[4] + System.lineSeparator();

        Ui.printMessageWithSepNewLine(message);
        return message;
    }

    /**
     * Add user created exercise into the existing list
     *
     * @param commandArgs A list of Strings that contain parameters for the Exercise object
     * */
    public void addExercises(String[] commandArgs) {
        assert commandArgs.length == REQUIRED_NUM_OF_PARAMETERS :
            "Something went wrong with parsing fitness add command arguments";

        Exercise newExercise = allExercises.newExercise(commandArgs);
        allExercises.add(newExercise);

        Ui.printMessageWithSepNewLine(ADD_EXERCISE_MESSAGE + newExercise);
    }

    /**
     * Gets all the exercises that belong to the queried type, and prints it to the UI.
     *
     * @param type An object of type ExerciseType used for query
     * */
    public void getTypeExercises(ExerciseType type) {
        ArrayList<Exercise> exercisesByType = allExercises.getType(type);
        String message = "Here are the " + type + " exercises as requested!" +
                System.lineSeparator();
        Ui.printList(exercisesByType, message);
    }

    /**
     * Delete the exercise specified by the user, and prints the remaining exercises of the same
     * type to the UI.
     *
     * @param commandArgs A list of strings that contain the index and ExerciseType to be deleted.
     * */
    public void deleteExercise(String[] commandArgs) {
        assert commandArgs.length == REQUIRED_DELETE_PARAMS :
                "Something went wrong with parsing fitness delete command arguments";

        ExerciseType type = ExerciseType.valueOf(commandArgs[0]);
        int index = Integer.parseInt(commandArgs[1]);

        if (allExercises.size(type) > 1) {
            allExercises.remove(allExercises.get(type, index - 1));
            ArrayList<Exercise> exercisesByType = allExercises.getType(type);

            Ui.printList(exercisesByType, DELETE_EXERCISE_MESSAGE);
        } else {
            Ui.printMessageWithSepNewLine(MIN_EXERCISES_MESSAGE);
        }
    }

    /**
     * Creates new exercise goals for the user. If goals already exists, it will override the
     * current goals with a set of new goals, then prints these goals to the Ui. Note that the
     * goals are 5 random exercises extracted from the list of exercises in the Motivator.
     * */
    public void newGoals() {
        dailyGoals.clear();
        Exercise[] exercises = fiveRandomExercises();

        for (Exercise e: exercises) {
            dailyGoals.add(e, false);
        }

        Ui.printMessageWithSepNewLine(NEW_GOAL_MESSAGE + dailyGoals.toString());
    }

    /**
     * Retrieves the status of current goals, then prints it to the Ui.
     * */
    public void goalStatus() {
        if (dailyGoals.isEmpty()) {
            Ui.printMessageWithSepNewLine(EMPTY_GOAL_MESSAGE);
        } else {
            Ui.printMessageWithSepNewLine(GOAL_MESSAGE + dailyGoals.toString());
        }
    }

    /**
     * Gets the goal that the user requested, and toggles the state of the goal. If it is done, it
     * will be marked as undone, and vice versa. All the goal status is then printed to the Ui.
     * */
    public void toggleGoal(int index) {
        if (dailyGoals.isEmpty()) {
            Ui.printMessageWithSepNewLine(EMPTY_GOAL_MESSAGE);
            return;
        }

        dailyGoals.findExercise(index - 1).toggle();
        dailyGoals.saveGoals();

        Ui.printMessageWithSepNewLine(GOAL_STATUS_MESSAGE + dailyGoals.toString());
    }

    /**
     * Prints a set of commands that the user can refer to in order to use the Fitness Motivator.
     * */
    public void printHelp() {
        ArrayList<String> helpMenuInstructionsList =
                new ArrayList<>(Arrays.asList(HELP_MENU_INSTRUCTIONS));

        Ui.printList(helpMenuInstructionsList, HELP_MESSAGE);
    }
}
