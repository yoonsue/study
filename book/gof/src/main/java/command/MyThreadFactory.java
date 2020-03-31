package command;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadFactory;

class MyThreadFactory implements ThreadFactory {

    private static final Set<Thread> threads = new HashSet<Thread>();

    static boolean created(Thread t) {
        synchronized (threads) {
            return threads.contains(t);
        }
    }

    public MyThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
