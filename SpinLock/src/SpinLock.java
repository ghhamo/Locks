import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLock {
    private final AtomicBoolean locker = new AtomicBoolean(false);

    public void lock() {
        while (true) {
            if (locker.compareAndSet(false, true)) {
                break;
            }
        }
    }

    public void unlock() {
        locker.set(false);
    }
}
