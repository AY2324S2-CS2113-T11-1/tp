package habit;

import exceptions.HabitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabitTrackerTest {
    private HabitTracker habitTracker;
    @BeforeEach
    public void setUp() {
        habitTracker = new HabitTracker();
    }

    @Test
    public void addHabit_addTwoHabits_success() {
        Habit habitOne = new Habit("Complete my homework");
        Habit habitTwo = new Habit("Brush my teeth");
        habitTracker.addHabit(habitOne);
        habitTracker.addHabit(habitTwo);
        assertEquals(2, HabitTracker.getNumberOfHabits());
    }

    @Test
    public void updateHabitCount_habitCountTwo_success() throws HabitException {
        Habit habit = new Habit("Complete my homework");
        habit.updateCount("2");
        assertEquals(2, habit.getHabitCount());
    }
}
