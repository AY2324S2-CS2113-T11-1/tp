package reflect;

import exceptions.ReflectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reflection.ReflectionManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReflectionManagerTest {

    private ReflectionManager reflectionManager;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        reflectionManager = new ReflectionManager();
    }

    @Test
    void printFiveRandomQuestions_printAnyFiveQuestions_success() {
        reflectionManager.printFiveRandomQuestions();

        // Check that exactly 5 questions, 1 header and 2 separation lines
        // have been printed to console
        String[] lines = outputStreamCaptor.toString().trim().split("\r\n|\r|\n");
        assertFalse(outputStreamCaptor.toString().trim().isEmpty());
        assertEquals(8, lines.length);
    }

    @Test
    void unsaveReflectionQuestion_unsaveFromNonEmptyFavouritesList_success() throws ReflectException {
        //Add question to favourites list and delete it after
        reflectionManager.printFiveRandomQuestions();
        reflectionManager.saveReflectionQuestion(1);
        assertDoesNotThrow(() -> reflectionManager.unsaveReflectionQuestion(1));
    }

    @Test
    void saveReflectionQuestion_saveFavouriteAfterGenerateQuestions_success() throws ReflectException {
        //Save question after questions have been generated
        reflectionManager.printFiveRandomQuestions();
        assertDoesNotThrow(() -> reflectionManager.saveReflectionQuestion(1));
    }

    @Test
    void saveReflectionQuestion_failToSaveWhenNoQuestionsGenerated_throwReflectException() throws ReflectException {
        //Unable to save as question has not been generated yet
        assertThrows(ReflectException.class, () -> reflectionManager.saveReflectionQuestion(1));
    }

    @Test
    void printHelpMenu_printHelpMenu_success() {
        reflectionManager.printHelpMenu();

        // Check that exactly 5 help menu items, 1 header and 2 separation lines
        // have been printed to console
        String[] lines = outputStreamCaptor.toString().trim().split("\r\n|\r|\n");
        assertFalse(outputStreamCaptor.toString().trim().isEmpty());
        assertEquals(8, lines.length);
    }

    @Test
    void saveReflectionQuestion_indexOutOfBounds_throwsReflectException() {
        // Users are only allowed to input id numbers between 1 and 5
        // as each list of newly generated questions is size 5
        assertThrows(ReflectException.class, () -> {
            reflectionManager.saveReflectionQuestion(6);
        });
    }
}

