package focus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountdownTimerTest {
    private CountdownTimer countdownTimer;

    @BeforeEach
    public void setUp() {
        countdownTimer = new CountdownTimer();
    }

    @Test
    public void setStartTimer_startTimer_success() {
        countdownTimer.setStart();
        assertEquals(true, countdownTimer.getRunningStatus());
    }

    @Test
    public void setStopTimer_stopTimer_success() {
        countdownTimer.setStart();
        countdownTimer.setStop();
        assertEquals(false, countdownTimer.getRunningStatus());
    }

    @Test
    public void setPause_pauseTimer_success() {
        countdownTimer.setStart();
        countdownTimer.setPause();
        assertEquals(false, countdownTimer.getPausedStatus());
    }

    @Test
    public void setResume_resumeTimer_success() {
        countdownTimer.setStart();
        countdownTimer.setPause();
        countdownTimer.setResume();
        assertEquals(true, countdownTimer.getPausedStatus());
    }

}
