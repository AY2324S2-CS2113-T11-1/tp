package fitness.goals;

import fitness.FitnessMotivator;
import fitness.exercise.Exercise;
import fitness.exercise.ExerciseList;
import fitness.exercise.ExerciseType;
import storage.Storage;

import java.util.ArrayList;

import static fitness.FitnessMotivator.GOALS_FILE_PATH;
import static fitness.FitnessMotivator.REQUIRED_NUM_OF_PARAMETERS;

/**
 * Represents the list of exercises and includes methods to manipulate the list of exercise goals.
 * Inherits from the ExerciseList class.
 * */
public class ExerciseGoalList extends ExerciseList {

    private static final int NUMBER_OF_GOALS = 5;
    private ArrayList<ExerciseGoal> goals = new ArrayList<>();

    public ExerciseGoalList() {
        if (Storage.isFileCreated(FitnessMotivator.GOALS_FILE_PATH)) {
            parseData(Storage.loadDataFromFile(FitnessMotivator.GOALS_FILE_PATH));
        }
    }

    /**
     * Further parses data read from storage into usable ExerciseGoal objects, before adding it into
     * the ArrayList.
     *
     * @param data An ArrayList of strings, comprising lines read from the data file
     * */
    private void parseData (ArrayList<String> data) {
        assert data.size() == NUMBER_OF_GOALS : "There is something wrong with the data file!";

        for (String s : data) {
            String[] parts = s.split(": |, | sets & | reps");

            // Checks whether the goal is done or not done
            char status = parts[0].charAt(1);
            boolean isDone = (status == 'X');
            parts[0] = parts[0].substring(4);

            if (parts.length == REQUIRED_NUM_OF_PARAMETERS) {
                goals.add(new ExerciseGoal(
                        parts[1],
                        ExerciseType.valueOf(parts[0].toUpperCase()),
                        parts[2],
                        parts[3],
                        isDone
                ));
            }
        }
    }

    /**
     * A helper method that returns whether the goal list is empty
     * */
    public boolean isEmpty() {
        return goals.isEmpty();
    }

    /**
     * A helper method that deletes everything in the list.
     * */
    public void clear() {
        goals.clear();
    }


    /**
     * Helper methods for finding exercises in the list. Overloaded with different parameters to
     * allow for different searching methods.This method uses index to search.
     *
     * @param index The n-th exercise of type ExerciseType, where n is the index
     *
     * @return An object of type Exercise
     * */
    public ExerciseGoal findExercise(int index) {
        return goals.get(index);
    }

    /**
     * Helper method that saves the goal data to a local file
     * */
    public void saveGoals() {
        Storage.saveTasksToFile(GOALS_FILE_PATH, goals);
    }

    /**
     * Over-ridden method from parent class 'ExerciseList', this method returns an 'ExerciseGoal'
     * object instead of an 'Exercise' object. The status of the goal is set not done by default.
     *
     * @param parameters An array of Strings that provide details for the creation of an Exercise
     *                   object.
     *                   Index 0 - Exercise Type
     *                   Index 1 - Exercise Name
     *                   Index 2 - Number of Sets
     *                   Index 3 - Number of Reps
     *
     * @return returns a new Exercise object
     * */
    @Override
    public ExerciseGoal newExercise(String[] parameters) {
        assert parameters.length == REQUIRED_NUM_OF_PARAMETERS
                : "Incorrect Parameters for a new Exercise Object";

        String type = parameters[0].toUpperCase();
        ExerciseType exerciseType = ExerciseType.valueOf(type);
        String exerciseName = parameters[1].trim();
        String sets = parameters[2].trim();
        String reps = parameters[3].trim();

        return new ExerciseGoal(exerciseName, exerciseType, sets, reps, false);
    }

    /**
     * Over-riden method from parent class 'ExerciseList', this method takes in 'Exercise' objects
     * and converts them into 'ExerciseGoal' objects before adding them into the goals list. The
     * status of the goal is set not done by default.
     *
     * @param exercise An Exercise object to be converted and added into the list
     * */
    @Override
    public void add(Exercise exercise) {
        goals.add(new ExerciseGoal(
                exercise.getExerciseName(),
                exercise.getType(),
                exercise.getSets(),
                exercise.getReps(),
                false
        ));

        Storage.saveTasksToFile(FitnessMotivator.GOALS_FILE_PATH, goals);
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < NUMBER_OF_GOALS; i++) {
            ExerciseGoal goal = goals.get(i);
            message.append((i + 1))
                .append(". ")
                .append(goal.toString())
                .append(System.lineSeparator());
        }
        return message.toString();
    }
}
