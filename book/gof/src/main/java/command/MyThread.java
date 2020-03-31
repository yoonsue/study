package command;

import java.security.AccessControlContext;

class MyThread extends Thread implements MyRunnable {
    private volatile String name;
    private int priority;

    private boolean daemon = false;

    private ThreadGroup group;

    private Runnable target;

    private final long stackSize;
    private final long tid;

    private volatile int threadStatus;

    private static int threadInitNumber;
    private static synchronized int nextThreadNum() {
        return threadInitNumber++;
    }

    public MyThread(ThreadGroup g, Runnable target, String name,
                  long stackSize, AccessControlContext acc,
                  boolean inheritThreadlocals) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        this.name = name;

        Thread parent = currentThread();
        SecurityManager security = System.getSecurityManager();
        if (g == null) {
            if (security != null) {
                g = security.getThreadGroup();
            }

            if (g == null) {
                g = parent.getThreadGroup();
            }
        }

        g.checkAccess();

//        g.addUnstarted();

        this.group = g;
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        this.target = target;
//        setPriority(priority);
        this.stackSize = stackSize;

        this.tid = nextThreadID();
    }

    public MyThread(Runnable target) {
        this(null, target, "Thread-" + nextThreadNum(), 0, null, false);
    }

    MyThread(Runnable target, AccessControlContext acc) {
        this(null, target, "Thread-" + nextThreadNum(), 0, acc, false);
    }

    private static long threadSeqNumber;

    private static synchronized long nextThreadID() {
        return ++threadSeqNumber;
    }

    public static native Thread currentThread();

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }
}
