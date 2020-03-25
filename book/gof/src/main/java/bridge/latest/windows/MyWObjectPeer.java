package bridge.latest.windows;

abstract class MyWObjectPeer {

    static {
        initIDs();
    }

    volatile Object target;

    private static native void initIDs();
}
