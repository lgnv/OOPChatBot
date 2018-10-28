package First.BotLogic;

import java.util.Date;

public class TimeChecker {
    private long initialTime;

    public TimeChecker() {
        updateInitialTime();
    }

    private int getDifferenceInHours(long oldTime, long newTime){
        return (int)(Math.abs(newTime - oldTime)) / (1000 * 60 * 60);
    }

    public boolean needToUpdate() {
        var needToUpdate = getDifferenceInHours(initialTime, new Date().getTime()) >= 24;
        if (needToUpdate) {
            updateInitialTime();
        }
        return needToUpdate;
    }

    private void updateInitialTime() {
        initialTime = new Date().getTime();
    }
}
