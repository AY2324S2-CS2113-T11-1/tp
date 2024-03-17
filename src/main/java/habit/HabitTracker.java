package habit;

import exceptions.HabitException;
import ui.Ui;

import java.util.ArrayList;


import static ui.Ui.printMessageWithoutSepNewLine;

public class HabitTracker {
    private static ArrayList<Habit> habitList = new ArrayList<>();

    public HabitTracker() {
    }


    public void addHabit (Habit newHabit) {
        habitList.add(newHabit);
        String addHabitMessage = "Great! You have added a new habit:" + System.lineSeparator();
        addHabitMessage += "  '" + newHabit.description + "' was successfully added!";
        Ui.printMessageWithSepNewLine(addHabitMessage);
    }

    public void listHabits() {
        String listHabitsMessage = "Here is the list of all your habits!" + System.lineSeparator();
        if (habitList.isEmpty()) {
            listHabitsMessage += "  <you currently have no habits, add one now!>" + System.lineSeparator();
        }
        for (int i = 0; i < habitList.size(); i++) {
            Habit habit = habitList.get(i);
            listHabitsMessage += "  " + (i + 1) + ". " + habit + System.lineSeparator();
        }
        printMessageWithoutSepNewLine(listHabitsMessage);
    }

    public static boolean isValidHabitID(int taskNumber) {
        return taskNumber > 0 && taskNumber <= habitList.size();
    }


    public void updateHabitCount(String habitIDString, String updatedCount) throws HabitException {
        int habitID;
        try {
            habitID = Integer.parseInt(habitIDString);
            if (!isValidHabitID(habitID)) {
                throw new HabitException("Please provide a valid habit ID.");
            }
        } catch (NumberFormatException e) {
            throw new HabitException("Please provide a valid habit ID.");
        }

        Habit habit = habitList.get(habitID - 1);
        int changeInCount = habit.updateCount(updatedCount);
        String updateHabitCountMessage = "";
        if (changeInCount > 0) {
            updateHabitCountMessage += "Good Job! You have completed your habit!" + System.lineSeparator();
        }
        updateHabitCountMessage += "The count for your habit has been updated:" + System.lineSeparator();
        updateHabitCountMessage += "  " + habitID + ". " + habit;
        Ui.printMessageWithSepNewLine(updateHabitCountMessage);
    }

    public static int getNumberOfHabits() {
        return habitList.size();
    }
}
