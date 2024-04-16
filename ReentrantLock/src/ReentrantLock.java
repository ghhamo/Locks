import java.util.concurrent.atomic.AtomicBoolean;

public class ReentrantLock {
    private Object o;
    private int capacity;

    public ReentrantLock() {
        capacity = 0;
    }

    private final AtomicBoolean locker = new AtomicBoolean(false);

    public void lock() throws InterruptedException {
        if (o == Thread.currentThread()) {
            capacity++;
            return;
        }
        while (!locker.compareAndSet(false, true)) {
            wait();
        }
        o = Thread.currentThread();
    }

    public void unlock() {
        if (Thread.currentThread() == o) {
            capacity--;
            if (capacity == 0) {
                notify();
                o = null;
                locker.set(false);
            }
        }
    }
}
