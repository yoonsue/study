package command;

import org.junit.jupiter.api.Test;

// TODO: Something wrong in code.
class ThreadTest implements Runnable {

    @Test
    public void start(MyThreadFactory threadFactory) {
        if (threadFactory == null) {
            threadFactory = new MyThreadFactory();
        }

        // now that there's at least one cleaning action, for the cleaner,
        // we can start the associated thread, which runs until
        // all cleaning actions have been run.
        MyThread thread = (MyThread) threadFactory.newThread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {

    }
}