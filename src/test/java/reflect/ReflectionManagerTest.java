package reflect;

import exceptions.ReflectException;
import org.junit.jupiter.api.AfterEach;
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
        String[] lines = outputStreamCaptor.toString().trim().split("\r\n|\r|\n");
        assertFalse(outputStreamCaptor.toString().trim().isEmpty());
        assertEquals(8, lines.length);
    }

    @Test
    void unsaveReflectionQuestion_unsaveFromNonEmptyFavouritesList_success() throws ReflectException {
        reflectionManager.printFiveRandomQuestions();
        reflectionManager.saveReflectionQuestion(1);
        assertDoesNotThrow(() -> reflectionManager.unsaveReflectionQuestion(1));
    }

    @Test
    void saveReflectionQuestion_saveFavouriteAfterGenerateQuestions_success() throws ReflectException {
        reflectionManager.printFiveRandomQuestions();
        assertDoesNotThrow(() -> reflectionManager.saveReflectionQuestion(1));
    }

    @Test
    void saveReflectionQuestion_failToSaveWhenNoQuestionsGenerated_throwReflectException() throws ReflectException {
        assertThrows(ReflectException.class, () -> reflectionManager.saveReflectionQuestion(1));
    }

    @Test
    void printHelpMenu_printHelpMenu_success() {
        reflectionManager.printHelpMenu();
        String[] lines = outputStreamCaptor.toString().trim().split("\r\n|\r|\n");
        assertFalse(outputStreamCaptor.toString().trim().isEmpty());
        assertEquals(8, lines.length);
    }

    @Test
    void saveReflectionQuestion_indexOutOfBounds_throwsReflectException() {
        assertThrows(ReflectException.class, () -> {
            reflectionManager.saveReflectionQuestion(6);
        });
    }

    @Test
    void unsaveReflectionQuestion_indexOutOfBounds_throwsReflectException() {
        assertThrows(ReflectException.class, () -> {
            reflectionManager.unsaveReflectionQuestion(6);
        });
    }
}

