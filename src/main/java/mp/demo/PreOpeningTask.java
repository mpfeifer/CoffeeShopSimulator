package mp.demo;

import java.util.concurrent.CountDownLatch;

public class PreOpeningTask implements Runnable {

    private CountDownLatch countDownLatch;
    private String description;
    private LoggingService loggingService = new LoggingService();
    
    public PreOpeningTask(String description, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.description = description;
    }

    public final void run() {
        loggingService.log(description);
        this.countDownLatch.countDown();
    }
}
