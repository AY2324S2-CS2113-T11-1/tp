package commands.reflectcommands;

import exceptions.ReflectException;
import org.junit.jupiter.api.Test;
import reflection.ReflectionManager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UnsaveFromFavouritesCommandTest {
    @Test
    void constructor_validReflectionId_success() {
        ReflectionManager reflectionManager = new ReflectionManager();
        assertDoesNotThrow(() -> new UnsaveFromFavouritesCommand(reflectionManager, "1"));
    }

    @Test
    void constructor_invalidReflectionId_throwReflectException() {
        ReflectionManager reflectionManager = new ReflectionManager();
        assertThrows(ReflectException.class, () -> new UnsaveFromFavouritesCommand(reflectionManager, "invalidId"));
    }

    @Test
    void testIsExit() throws ReflectException {
        ReflectionManager reflectionManager = new ReflectionManager();
        UnsaveFromFavouritesCommand saveCommand = new UnsaveFromFavouritesCommand(reflectionManager, "1");
        assertFalse(saveCommand.isExit());
    }
}
