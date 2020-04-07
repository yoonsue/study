package observer;

public abstract class MyTimerTask implements Runnable {

    final Object lock = new Object();

    int state = VIRGIN;

    static final int VIRGIN = 0;
    static final int SCHEDULED = 1;
    static final int EXECUTED = 2;
    static final int CANCELLED = 3;

    long nextExecutionTime;

    long period = 0;

    protected MyTimerTask() {
    }

    public abstract void run();


}
