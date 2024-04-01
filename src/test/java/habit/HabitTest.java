package habit;

import exceptions.HabitException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HabitTest {

    @Test
    public void updateCount_increaseCount_success() throws HabitException {
        Habit habit = new Habit("Exercise");
        int changeInCount = habit.updateCount("2");
        assertEquals(2, habit.getHabitCount());
        assertEquals(2, changeInCount);
    }

    @Test
    public void updateCount_decreaseCount_success() throws HabitException {
        Habit habit = new Habit("Exercise", 3, Priority.HIGH);
        int changeInCount = habit.updateCount("-2");
        assertEquals(1, habit.getHabitCount());
        assertEquals(-2, changeInCount);
    }

    @Test
    public void updateCount_invalidInput_throwException() {
        Habit habit = new Habit("Exercise", 3, Priority.HIGH);
        assertThrows(HabitException.class, () -> {
            habit.updateCount("invalid");
        });
    }

    @Test
    public void updateCount_negativeCount_throwException() {
        Habit habit = new Habit("Exercise", 3, Priority.HIGH);
        assertThrows(HabitException.class, () -> {
            habit.updateCount("-4");
        });
    }

    @Test
    public void setPriority_validInput_success() {
        Habit habit = new Habit("Exercise");
        habit.setPriority("high");
        assertEquals(Priority.HIGH, habit.getPriority());
    }

    @Test
    public void setPriority_invalidInput_noChange() {
        Habit habit = new Habit("Exercise");
        habit.setPriority("invalid");
        assertEquals(Priority.LOW, habit.getPriority());
    }
}
