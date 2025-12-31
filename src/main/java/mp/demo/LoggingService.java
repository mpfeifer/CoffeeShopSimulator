package mp.demo;

public class LoggingService {

    private static final Object lock = new Object();

    void log(String message) {
        synchronized (lock) {
            System.out.println(message);
        }
    }
}
