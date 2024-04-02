package habit;

import exceptions.HabitException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabitTrackerTest {
    private HabitTracker habitTracker;
    @BeforeEach
    public void setUp() {
        habitTracker = new HabitTracker();
    }

    @AfterEach
    public void tearDown() {
        habitTracker.clearHabitList();
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
        Habit habitOne = new Habit("Complete my homework");
        habitTracker.addHabit(habitOne);
        habitTracker.updateHabitCount(1, "2");
        assertEquals(2, habitOne.getHabitCount());
    }

    @Test
    public void deleteHabit_deleteOneHabit_success() throws HabitException {
        Habit habitOne = new Habit("Vacuum the floor");
        habitTracker.addHabit(habitOne);
        habitTracker.deleteHabit(1);
        assertEquals(0, HabitTracker.getNumberOfHabits());
    }

    @Test
    public void setPriorityLevel_setPriorityToHigh_success() throws HabitException {
        Habit habitOne = new Habit("Complete my homework");
        habitTracker.addHabit(habitOne);
        habitTracker.setPriorityLevel(1, "high");
        assertEquals(Priority.HIGH, habitOne.getPriority());
    }

    @Test
    public void sortHabits_sortByPriority_success() throws HabitException {
        Habit habitOne = new Habit("Complete my homework", 2, Priority.LOW);
        Habit habitTwo = new Habit("Brush my teeth", 1, Priority.MED);
        habitTracker.addHabit(habitOne);
        habitTracker.addHabit(habitTwo);

        habitTracker.sortHabits();

        assertEquals(Priority.MED, habitTracker.getHabitList().get(0).getPriority());
        assertEquals(Priority.LOW, habitTracker.getHabitList().get(1).getPriority());
    }


}
