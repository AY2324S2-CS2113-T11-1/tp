package fitness.goals;

import fitness.exercise.Exercise;
import fitness.exercise.ExerciseType;

public class ExerciseGoal extends Exercise {

    private boolean isDone;

    public ExerciseGoal(
            String exerciseName,
            ExerciseType exerciseType,
            String sets,
            String reps,
            boolean isDone) {
        super(exerciseName, exerciseType, sets, reps);
        this.isDone = isDone;
    }

    public void toggle() {
        isDone = !isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? "[x] " : "[ ] ";
        return status + exerciseType + ": " + exerciseName + ", " +
                sets + " sets & " + reps + " reps";
    }
}
