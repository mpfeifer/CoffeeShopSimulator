package mp.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class App {
    
    public static void main(String[] args) {

        ExecutorService coffeeMachineExecutor = Executors.newFixedThreadPool(2);

        String[] preOpeningTasksNames = { "Inserting Beans", "Cleaning Machine", "Inserting Milk",
                "Turning Machine On" };

        CountDownLatch preOpeningTasks = new CountDownLatch(preOpeningTasksNames.length);

        ExecutorService preOpeningExecutor = Executors.newFixedThreadPool(1);

        final int numOrders = 15;

        String[] namePool = {
                "Ace", "Ada", "Ari", "Ben", "Bo", "Cas", "Dan", "Eli",
                "Fay", "Gia", "Ian", "Jax", "Kai", "Leo", "Mia", "Noa",
                "Pax", "Ray", "Sky", "Tia", "Uli", "Val", "Wes", "Zoe"
        };

        for (int i = 0 ; i < numOrders; i++) {
            String name = namePool[ (int) (Math.round(Math.random() * namePool.length))];
            coffeeMachineExecutor.submit(new CoffeeOrder(name, 1500, preOpeningTasks));
        }

        for (String task : preOpeningTasksNames) {
            PreOpeningTask preOpeningTask = new PreOpeningTask(task, preOpeningTasks);
            preOpeningExecutor.submit(preOpeningTask);
        }

        preOpeningExecutor.shutdown();
        coffeeMachineExecutor.shutdown();
    }
}
