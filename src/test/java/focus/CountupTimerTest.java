package focus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountupTimerTest {
    private CountupTimer countupTimer;

    @BeforeEach
    public void setUp() {
        countupTimer = new CountupTimer();
    }

    @Test
    public void setStartTimer_startTimer_success() {
        countupTimer.setStartTiming();
        assertEquals(true, countupTimer.getStartedStatus());
    }

    @Test
    public void setStopTimer_stopTimer_success() {
        countupTimer.setStartTiming();
        countupTimer.setStopTiming();
        assertEquals(false, countupTimer.getStartedStatus());
    }

    @Test
    public void setPause_pauseTimer_success() {
        countupTimer.setStartTiming();
        countupTimer.setPause();
        assertEquals(true, countupTimer.getPauseStatus());
    }

    @Test
    public void setResume_resumeTimer_success() {
        countupTimer.setStartTiming();
        countupTimer.setPause();
        countupTimer.setResume();
        assertEquals(false, countupTimer.getPauseStatus());
    }
}
