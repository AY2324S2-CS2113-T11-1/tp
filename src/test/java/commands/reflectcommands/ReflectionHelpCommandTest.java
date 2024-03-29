package commands.reflectcommands;

import exceptions.ReflectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reflection.ReflectionManager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReflectionHelpCommandTest {

    private ReflectionManager reflectionManager;
    private ReflectionHelpCommand command;

    @BeforeEach
    void setUp() throws ReflectException {
        reflectionManager = new ReflectionManager();
        command = new ReflectionHelpCommand(reflectionManager, "");
    }

    @Test
    void constructor_emptyArgs_noExceptionThrown() {
        assertDoesNotThrow(() -> new ReflectionHelpCommand(reflectionManager, ""));
    }

    @Test
    void constructor_nonEmptyArgs_exceptionThrown() {
        assertThrows(ReflectException.class, () -> new ReflectionHelpCommand(reflectionManager, "args"));
    }

    @Test
    void isExit_notExitCommand_returnFalse() {
        assertFalse(command.isExit());
    }
}
