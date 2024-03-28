package fitness;

import commands.fitnesscommands.AddExerciseCommand;
import exceptions.FitnessException;
import exceptions.Wellness360Exception;
import fitness.exercise.Exercise;
import fitness.exercise.ExerciseList;
import fitness.exercise.ExerciseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;


import static commands.fitnesscommands.ErrorMessageConstants.INCORRECT_INTEGER_ERROR_MESSAGE;
import static commands.fitnesscommands.ErrorMessageConstants.INSUFFICIENT_PARAMS_ERROR_MESSAGE;
import static commands.fitnesscommands.ErrorMessageConstants.ILLEGAL_TYPE_ERROR_MESSAGE;
import static fitness.FitnessMotivator.DATA_FILE_PATH;
import static fitness.FitnessMotivator.GOALS_FILE_PATH;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static storage.Storage.isFileCreated;

public class FitnessMotivatorTest {

    private FitnessMotivator fitnessMotivator;
    private ExerciseList allExercises;

    @BeforeEach
    public void setUp() {
        File file1 = new File(DATA_FILE_PATH);
        File file2 = new File(GOALS_FILE_PATH);
        if (isFileCreated(DATA_FILE_PATH)) {
            file1.delete();
        }
        if (isFileCreated(GOALS_FILE_PATH)) {
            file2.delete();
        }
        this.fitnessMotivator = new FitnessMotivator();
        this.allExercises = fitnessMotivator.allExercises;
    }

    @Test
    public void printExercises_getExercises_success() {
        String output = fitnessMotivator.getExercises();
        assertTrue(output.contains("Arms"));
        assertTrue(output.contains("Chest"));
        assertTrue(output.contains("Abs"));
        assertTrue(output.contains("Back"));
        assertTrue(output.contains("Legs"));
        assertTrue(output.contains("sets"));
        assertTrue(output.contains("reps"));
    }

    @Test
    public void addExerciseIntoList_addExercises_success() {
        // Test input for adding exercise
        String[] exerciseDetails = {
            "Arms", "testing", "3", "10"
        };

        // Generating results before adding and after adding exercises
        Exercise exercise = new Exercise(
            "testing", ExerciseType.ARMS, "3", "10");
        Exercise searchResultBeforeAdding =
            allExercises.findExercise(ExerciseType.ARMS, "testing");
        fitnessMotivator.addExercises(exerciseDetails);
        Exercise searchResultAfterAdding =
            allExercises.findExercise(ExerciseType.ARMS, "testing");

        // Assertions
        assertNull(searchResultBeforeAdding);
        assertEquals(exercise.getExerciseName(), searchResultAfterAdding.getExerciseName());
        assertEquals(exercise.getType(), searchResultAfterAdding.getType());
        assertEquals(exercise.getSets(), searchResultAfterAdding.getSets());
        assertEquals(exercise.getReps(), searchResultAfterAdding.getReps());
    }

    @Test
    public void incorrectParameters_addExercises_success() {
        // Checks if the validation of input for add Exercise works
        Wellness360Exception exceptionOne = assertThrows(FitnessException.class, () ->
                new AddExerciseCommand(fitnessMotivator, "testing"));
        Wellness360Exception exceptionTwo = assertThrows(FitnessException.class, () ->
                new AddExerciseCommand(fitnessMotivator, "arms, testing, a, b"));
        Wellness360Exception exceptionThree = assertThrows(FitnessException.class, () ->
                new AddExerciseCommand(fitnessMotivator, "testing, testing, 3, 10"));

        assertEquals("ERROR MSG: " + INSUFFICIENT_PARAMS_ERROR_MESSAGE, exceptionOne.getMessage());
        assertEquals("ERROR MSG: " + INCORRECT_INTEGER_ERROR_MESSAGE, exceptionTwo.getMessage());
        assertEquals("ERROR MSG: " + ILLEGAL_TYPE_ERROR_MESSAGE, exceptionThree.getMessage());

    }
}
