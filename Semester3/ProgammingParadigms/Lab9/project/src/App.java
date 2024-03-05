import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static AtomicInteger global_variable = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        make_thread(5, 2); 

        Thread.sleep(TimeUnit.SECONDS.toMillis(3));

        System.out.println("Global variable: " + global_variable.get());

        ///
        bank bank = new bank();
        bank.bank_init();
        bank.run_bank();
        bank.print_bank();
    }

    public static void make_thread(int n, int m) {
        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            final int thread_id = i;
            threads[i] = new Thread(() -> {
                long endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(m);
                while (System.currentTimeMillis() < endTime) {
                    if (thread_id % 2 == 0) {
                        global_variable.incrementAndGet();
                    } else {
                        global_variable.decrementAndGet();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
