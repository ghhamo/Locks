import java.util.concurrent.atomic.AtomicBoolean;

public class NonBlockingLock {
    private final AtomicBoolean locker = new AtomicBoolean(false);

    public boolean lock() {
        return locker.compareAndSet(false, true);
    }

    public void unlock() {
        locker.set(false);
    }
}
