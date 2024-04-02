package commands;

import exceptions.Wellness360Exception;
import focus.FocusTimer;

public class ExitCommand implements Command {
    FocusTimer focustimer;

    public ExitCommand( FocusTimer timer, String commandArgs) throws Wellness360Exception {
        if (!commandArgs.isEmpty()) {
            throw new Wellness360Exception("Unknown command");
        }
        this.focustimer = timer;
    }

    @Override
    public void execute() {
        if(focustimer.timerMode) {
            focustimer.setStopTiming();
        }
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
