package fitness;

import fitness.exercise.Exercise;
import fitness.exercise.ExerciseList;
import fitness.exercise.ExerciseType;
import fitness.goals.ExerciseGoalList;
import ui.fitness.FitnessUi;

import java.util.ArrayList;
import java.util.Random;

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

        FitnessUi.printMessageWithSepNewLine(message);
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
        String message = "I have added the following exercise into our list!" +
                System.lineSeparator() + newExercise;
        FitnessUi.printMessageWithSepNewLine(message);
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
        FitnessUi.printList(exercisesByType, message);
    }

    public void newGoal() {
        Exercise[] exercises = fiveRandomExercises();
        for (Exercise e: exercises) {
            dailyGoals.add(e);
        }
        String message = "Lets get working on today's exercises!" + System.lineSeparator() +
                dailyGoals.toString();
        FitnessUi.printMessageWithSepNewLine(message);
    }

    public void goalStatus() {
        if (dailyGoals.isEmpty()) {
            String message = "There are no goals set :(" + System.lineSeparator() +
                    "You can set one by doing 'goal new'!";
            FitnessUi.printMessageWithSepNewLine(message);
        } else {
            FitnessUi.printMessageWithSepNewLine(dailyGoals.toString());
        }
    }

    public void toggleGoal() {
        // toggle progress of goals
    }

}
