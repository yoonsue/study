package bridge.latest.macosx;

import sun.awt.SunToolkit;

import java.awt.*;

public abstract class LWToolkit extends SunToolkit implements Runnable {

    protected LWToolkit() {
    }

    public static final Object targetToPeer(Object target) {
        return SunToolkit.targetToPeer(target);
    }

    public static void postEvent(AWTEvent event) {
        postEvent(targetToAppContext(event.getSource()), event);
    }
}
