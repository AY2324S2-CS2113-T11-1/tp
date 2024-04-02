package fitness.goals;

import fitness.exercise.Exercise;
import fitness.ExerciseType;

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

    public boolean getStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X] " : "[ ] ";
        return status + exerciseType + ": " + exerciseName + ", " +
                sets + " sets & " + reps + " reps";
    }
}
