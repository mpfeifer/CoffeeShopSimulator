package mp.demo;

import java.lang.Runnable;
import java.util.concurrent.CountDownLatch;

public class CoffeeOrder implements Runnable {

    private String orderName;
    private long brewTime;
    private CountDownLatch preOpeningTasks;
    private LoggingService loggingService = new LoggingService();
    
    public CoffeeOrder(String orderName, long brewTime, CountDownLatch preOpeningTasks) {
        this.orderName = orderName;
        this.brewTime = brewTime;
        this.preOpeningTasks = preOpeningTasks;
    }

    public final void run() {
        try {
            preOpeningTasks.await();
            loggingService.log("Brewing: " + this.orderName);
            Thread.sleep(brewTime);
            loggingService.log("Finished: " + this.orderName);
        } catch (InterruptedException e) {
            loggingService.log("Order \"" + this.orderName + "\" was interrupted.");
        } finally {
            // 
        }
    }

    public String getOrderName() {
        return this.orderName;
    }

    public long getBrewTime() {
        return this.brewTime;
    }
}
