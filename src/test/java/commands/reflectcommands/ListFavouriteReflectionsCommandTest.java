package commands.reflectcommands;

import exceptions.ReflectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reflection.ReflectionManager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListFavouriteReflectionsCommandTest {
    private ReflectionManager reflectionManager;
    private ListFavouriteReflectionsCommand command;

    @BeforeEach
    void setUp() throws ReflectException {
        reflectionManager = new ReflectionManager();
        command = new ListFavouriteReflectionsCommand(reflectionManager, "");
    }

    @Test
    void constructor_emptyArgs_noExceptionThrown() {
        assertDoesNotThrow(() -> new ListFavouriteReflectionsCommand(reflectionManager, ""));
    }

    @Test
    void constructor_nonEmptyArgs_exceptionThrown() {
        assertThrows(ReflectException.class, () -> new ListFavouriteReflectionsCommand(reflectionManager, "args"));
    }

    @Test
    void isExit_notExitCommand_returnFalse() {
        assertFalse(command.isExit());
    }
}

