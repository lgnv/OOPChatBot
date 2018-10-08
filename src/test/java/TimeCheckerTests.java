import static org.junit.jupiter.api.Assertions.*;

import First.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

class TimeCheckerTests {

    @Test
    void testGetMillisecondToHours(){
        assertEquals(1, getDifferenceInHours(3600000, 0));
        assertEquals(0, getDifferenceInHours(3600000, 1));
        assertEquals(3, getDifferenceInHours(3600000 * 3, 0));
        assertEquals(24, getDifferenceInHours(3600000 * 24 + 1, 0));
    }

    @Test
    void testUpdateByTimerAfter24() {
        var timeChecker = getTimeCheckerWithTimeDifference(1000 * 60 * 60 * 24);
        assertTrue(timeChecker.needToUpdate());
    }

    @Test
    void testUpdateByTimerAfter25() {
        var timeChecker = getTimeCheckerWithTimeDifference(1000 * 60 * 60 * 25);
        assertTrue(timeChecker.needToUpdate());
    }

    @Test
    void testNotUpdateByTimerAfter23() {
        var timeChecker = getTimeCheckerWithTimeDifference(1000 * 60 * 60 * 23);
        assertFalse(timeChecker.needToUpdate());
    }

    private TimeChecker getTimeCheckerWithTimeDifference(long passedTime) {
        var timeChecker = new TimeChecker();
        long oldTimer;
        try {
            var fieldTimer = timeChecker.getClass().getDeclaredField("downloadingTime");
            fieldTimer.setAccessible(true);
            oldTimer = (long) fieldTimer.get(timeChecker);
            fieldTimer.set(timeChecker, oldTimer - passedTime);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return timeChecker;
    }

    private int getDifferenceInHours(long oldTime, long newTime){
        var timeChecker = new TimeChecker();
        int result = 0;
        var classTimeChecker = timeChecker.getClass();
        try {
            var method = classTimeChecker.getDeclaredMethod("getDifferenceInHours", new Class[]{long.class, long.class});
            method.setAccessible(true);
            result =  (int)method.invoke(timeChecker, oldTime, newTime);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        return result;
    }
}
