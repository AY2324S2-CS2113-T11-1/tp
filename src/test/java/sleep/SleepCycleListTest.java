package sleep;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepCycleListTest {
    private SleepCycleList sleepCycleList;

    @BeforeEach
    public void setUp() {
        sleepCycleList = new SleepCycleList();
    }

    @Test
    public void addSleepCycle_addCycle_success() {
        LocalDate date;
        date = LocalDate.parse("25/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy") );
        SleepCycle cycle = new SleepCycle(2, date);
        sleepCycleList.addSleepCycle(cycle, false);
        assertEquals(1, sleepCycleList.getNumberOfCycles());
    }

    @Test
    public void addTwoSleepCycle_getTotalHoursSlept_success() {
        LocalDate date1;
        LocalDate date2;
        date1 = LocalDate.parse("25/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        date2 = LocalDate.parse("26/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        SleepCycle cycle = new SleepCycle(2, date1);
        sleepCycleList.addSleepCycle(cycle, false);
        cycle = new SleepCycle(3, date2);
        sleepCycleList.addSleepCycle(cycle,false);
        assertEquals(5, sleepCycleList.getTotalHrsSlept());
    }

    @Test
    public void deleteSleepCycles_success() {
        LocalDate date1;
        LocalDate date2;
        LocalDate date3;
        LocalDate date4;
        date1 = LocalDate.parse("25/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        date2 = LocalDate.parse("26/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        date3 = LocalDate.parse("27/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        date4 = LocalDate.parse("28/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        SleepCycle cycle = new SleepCycle(2, date1);
        sleepCycleList.addSleepCycle(cycle, false);
        cycle = new SleepCycle(3, date2);
        sleepCycleList.addSleepCycle(cycle,false);
        cycle = new SleepCycle(4, date3);
        sleepCycleList.addSleepCycle(cycle,false);
        cycle = new SleepCycle(5, date4);
        sleepCycleList.addSleepCycle(cycle,false);

        sleepCycleList.deleteSleepCycle(date1);
        assertEquals(3, sleepCycleList.getNumberOfCycles());
        sleepCycleList.deleteSleepCyclesBefore(date3);
        assertEquals(2, sleepCycleList.getNumberOfCycles());
        sleepCycleList.deleteSleepCyclesBetween(date3, date4);
        assertEquals(0, sleepCycleList.getNumberOfCycles());
    }

    @Test
    public void updateSleepCycle_success() {
        LocalDate date;
        date = LocalDate.parse("25/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy") );
        SleepCycle cycle = new SleepCycle(2, date);
        sleepCycleList.addSleepCycle(cycle, false);
        assertEquals(1, sleepCycleList.getNumberOfCycles());
        sleepCycleList.updateSleepCycle(date, 5);
        assertEquals(5, sleepCycleList.getTotalHrsSlept());
    }
}
