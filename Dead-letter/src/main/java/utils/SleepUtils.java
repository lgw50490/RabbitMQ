package utils;

/**
 * @author zg
 */
public class SleepUtils {
    public static void sleep(int second) {
        try {
            Thread.sleep(1000L * second);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}