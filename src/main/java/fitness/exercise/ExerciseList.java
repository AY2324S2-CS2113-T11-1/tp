package fitness.exercise;

import fitness.FitnessMotivator;
import storage.Storage;

import java.util.ArrayList;
import java.util.Comparator;

import static fitness.FitnessMotivator.REQUIRED_NUM_OF_PARAMETERS;
import static fitness.exercise.ExerciseBank.INIT_ARMS_EXERCISES;
import static fitness.exercise.ExerciseBank.INIT_CHEST_EXERCISES;
import static fitness.exercise.ExerciseBank.INIT_ABS_EXERCISES;
import static fitness.exercise.ExerciseBank.INIT_BACK_EXERCISES;
import static fitness.exercise.ExerciseBank.INIT_LEGS_EXERCISES;

/**
 * Represents the list of exercises and includes methods to manipulate the list
 * */
public class ExerciseList {

    private ArrayList<Exercise> allExercises = new ArrayList<>();

    /**
     * Checks if a save file exists, if it does then load it for use, else create a new data file
     * and initialise it with the data above.
     * */
    public ExerciseList() {
        if (!Storage.isFileCreated(FitnessMotivator.DATA_FILE_PATH)) {
            initialiseData();
            Storage.saveTasksToFile(FitnessMotivator.DATA_FILE_PATH, allExercises);
        } else {
            parseData(Storage.loadDataFromFile(FitnessMotivator.DATA_FILE_PATH));
        }
    }

    /**
     * Takes in a pre-saved list of exercises for a single exercise type
     *
     * @param list An array of strings that contain information about all exercises under each type
     * */
    private void initialiseSingleList(String[] list, ExerciseType type) {
        for (String s : list) {
            String[] exerciseDetails = s.split(",");
            assert exerciseDetails.length == REQUIRED_NUM_OF_PARAMETERS
                    : "Missing Data from Data file!";
            Exercise exercise = new Exercise(exerciseDetails[0], type,
                    exerciseDetails[2], exerciseDetails[3]);
            allExercises.add(exercise);
        }
    }

    /**
     * Reads all 5 different string arrays from above and adds it into one ArrayList for use
     * */
    private void initialiseData() {
        initialiseSingleList(INIT_ARMS_EXERCISES, ExerciseType.ARMS);
        initialiseSingleList(INIT_CHEST_EXERCISES, ExerciseType.CHEST);
        initialiseSingleList(INIT_ABS_EXERCISES, ExerciseType.ABS);
        initialiseSingleList(INIT_BACK_EXERCISES, ExerciseType.BACK);
        initialiseSingleList(INIT_LEGS_EXERCISES, ExerciseType.LEGS);
    }

    /**
     * Further parses data read from storage into usable exercise objects, before adding it into
     * the ArrayList.
     *
     * @param data An ArrayList of strings, comprising lines read from the data file
     * */
    private void parseData (ArrayList<String> data) {
        for (String s: data) {
            String[] parts = s.split(": |, | sets & | reps");
            if (parts.length == REQUIRED_NUM_OF_PARAMETERS) {
                allExercises.add(newExercise(parts));
            }
        }
    }

    /**
     * This method adds an exercise object into the full list of exercises. It also sorts the list
     * in order of exercise type before saving it into storage.
     *
     * @param exercise An Exercise object for ExerciseList
     */
    public void add(Exercise exercise) {
        allExercises.add(exercise);

        // The comparing method extracts the exercise type from each exercise object and then
        // compares them based on their type
        Comparator<Exercise> comparator = Comparator.comparing(Exercise::getType);

        // The sort method then sorts the list based on the comparator specified before saving
        allExercises.sort(comparator);
        Storage.saveTasksToFile(FitnessMotivator.DATA_FILE_PATH, allExercises);
    }


    /**
     * This method searches the ArrayList for Exercises that matches the required type, and returns
     * the n-th item of the queried type, where n is the index.
     *
     * @param type The ExerciseType Enum to be queried
     * @param index The n-th instance of all object that matches the queried ExerciseType
     *
     * @return Returns an Object of type Exercise that matches the type and index queried.
     * */
    public Exercise get(ExerciseType type, int index) {
        ArrayList<Exercise> typeExercises = new ArrayList<>();
        for (Exercise e : allExercises) {
            assert e != null : "Invalid Exercise Detected";
            if (e.getType().equals(type)) {
                typeExercises.add(e);
            }
        }
        return typeExercises.get(index);
    }

    public ArrayList<Exercise> getType(ExerciseType type) {
        ArrayList<Exercise> exercisesByType = new ArrayList<>();
        for (Exercise e : allExercises) {
            assert e != null : "Invalid Exercise Detected";
            if (e.getType().equals(type)) {
                exercisesByType.add(e);
            }
        }
        return exercisesByType;
    }

    /**
     * Returns the total number of a certain type of exercise
     *
     * @param type The ExerciseType Enum to be queried
     * */
    public int size(ExerciseType type) {
        int x = 0;
        for (Exercise e : allExercises) {
            if (e.getType().equals(type)) {
                x++;
            }
        }
        return x;
    }

    /**
     * Creates a new Exercise Object using an array of strings
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
    public Exercise newExercise(String[] parameters) {
        assert parameters.length == REQUIRED_NUM_OF_PARAMETERS
            : "Incorrect Parameters for a new Exercise Object";

        String type = parameters[0].toUpperCase();
        ExerciseType exerciseType = ExerciseType.valueOf(type);
        String exerciseName = parameters[1].trim();
        String sets = parameters[2].trim();
        String reps = parameters[3].trim();

        return new Exercise(exerciseName, exerciseType, sets, reps);
    }

    /**
     * Helper methods for finding exercises in the list. Overloaded with different parameters to
     * allow for different searching methods. This method uses the exercise name to search.
     *
     * @param type The ExerciseType Enum to be queried
     * @param nameQuery the name of the exercise to be queried
     *
     * @return An object of type Exercise
     * */
    public Exercise findExercise(ExerciseType type, String nameQuery) {
        ArrayList<Exercise> typeExercises = getType(type);
        for (Exercise e : typeExercises) {
            if (e.getType().equals(type) && e.getExerciseName().contains(nameQuery)) {
                return e;
            }
        }
        return null;
    }

}
