package commands.sleepcommands;

import commands.Command;
import exceptions.SleepException;


import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public class HelpSleepCommand implements Command{
    private static final String[] HELP_MENU_INSTRUCTIONS = {
        "sleep add [HOURS_SLEPT] /date [DATE_SLEPT]: Add a new sleep cycle",
        "sleep list: List out all sleep cycles",
        "sleep get [DATE_OF_SLEEP]: Get hours slept on a specific date",
        "sleep update [DATE_OF_SLEEP] /new [HOURS_OF_SLEEP]: Updates hours slept on a specific date",
        "sleep delete /date [DATE_OF_SLEEP]: Delete Sleep Cycle of a specific date",
        "sleep delete /before [DATE_OF_SLEEP]: Delete Sleep Cycles before a specific date",
        "sleep delete /from [START_DATE] /to [END_DATE]: Delete Sleep Cycles within a range of dates",
        "sleep save: Allow user to save sleep cycles in a text file located in FILE_PATH: data/sleep.txt"
    };

    public HelpSleepCommand(String reflectionCommandArgs)
            throws SleepException {
        if (!reflectionCommandArgs.isBlank()) {
            throw new SleepException("Additional parameters for 'sleep help' command are not allowed.");
        }
    }


    /**
     * Execute the command to display the habit tracker help menu
     */
    public void execute() {
        ArrayList<String> helpMenuInstructionsList = new ArrayList<>(Arrays.asList(HELP_MENU_INSTRUCTIONS));

        assert helpMenuInstructionsList.size() == 8 : "Help menu should have 8 instructions";

        Ui.printList(helpMenuInstructionsList, "Commands for sleep tracker feature:");
    }

    public boolean isExit() {
        return false;
    }
}
