package commands.focuscommands;

import commands.Command;
import ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Represents a command to display the focus timer help menu
 */
public class FocusHelpCommand implements Command {

    private static final String[] HELP_MENU_INSTRUCTIONS = {
        "focus swtich: Switch between count up timer and count down timer",
        "focus start: Start the timer",
        "focus stop: Stop the timer",
        "focus pause: Pause the timer",
        "focus resume: Resume the timer",
        "focus set [MINUTES]: Set the desired countdown timer duration in minutes",
    };

    @Override
    public void execute() {
        ArrayList<String> helpMenuInstructionsList = new ArrayList<>(Arrays.asList(HELP_MENU_INSTRUCTIONS));

        assert helpMenuInstructionsList.size() == 6 : "Help menu should have 6 instructions";

        Ui.printList(helpMenuInstructionsList, "Commands for focus timer feature:");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
