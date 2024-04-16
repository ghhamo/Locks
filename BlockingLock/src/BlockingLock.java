import java.util.concurrent.atomic.AtomicBoolean;
public class BlockingLock {
    private Object o;
    private final AtomicBoolean locker = new AtomicBoolean(false);

    public void lock() throws InterruptedException {
        while (!locker.compareAndSet(false, true)) {
            wait();
        }
        o = Thread.currentThread();
    }

    public void unlock() {
        if (Thread.currentThread() == o) {
            notify();
            o = null;
            locker.set(false);
        }
    }
}