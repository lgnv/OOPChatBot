package First;

import java.util.Date;

public class TimeChecker {
    private long downloadingTime;

    public TimeChecker() {
        updateDownloadingTime();
    }

    private static int getMillisecondToHours(long oldTime, long newTime){
        return (int)(Math.abs(newTime - oldTime)) / (1000 * 60 * 60);
    }

    public boolean needToUpdate() {
        var needToUpdate = getMillisecondToHours(downloadingTime, new Date().getTime()) >= 24;
        if (needToUpdate) {
            updateDownloadingTime();
        };
        return needToUpdate;
    }

    private void updateDownloadingTime() {
        downloadingTime = new Date().getTime();
    }
}
