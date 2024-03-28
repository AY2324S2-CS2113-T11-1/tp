package fitness.goals;

import fitness.FitnessMotivator;
import fitness.exercise.Exercise;
import fitness.exercise.ExerciseList;
import fitness.exercise.ExerciseType;
import storage.Storage;

import java.util.ArrayList;

import static fitness.FitnessMotivator.REQUIRED_NUM_OF_PARAMETERS;

public class ExerciseGoalList extends ExerciseList {
    private ArrayList<ExerciseGoal> goals = new ArrayList<>();
    private final int NUMBER_OF_GOALS = 5;

    public ExerciseGoalList() {
        if (Storage.isFileCreated(FitnessMotivator.GOALS_FILE_PATH)) {
            parseData(Storage.loadDataFromFile(FitnessMotivator.GOALS_FILE_PATH));
        }
    }

    private void parseData (ArrayList<String> data) {
        assert data.size() == NUMBER_OF_GOALS : "There is something wrong with the data file!";
        for (String s : data) {
            String[] parts = s.split(": |, | sets & | reps");
            if (parts.length == REQUIRED_NUM_OF_PARAMETERS) {
                goals.add(newExercise(parts));
            }
        }
    }

    public boolean isEmpty() {
        return goals.isEmpty();
    }

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
