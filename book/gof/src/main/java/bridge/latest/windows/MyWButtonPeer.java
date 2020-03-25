package bridge.latest.windows;

import bridge.latest.MyButtonPeer;

import java.awt.*;

final class MyWButtonPeer extends MyWComponentPeer implements MyButtonPeer, MyWComponentPeer {

    static {
        initIDs();
    }

    @Override
    public Dimension getMinimumSize() {
        FontMetrics fm = getFontMetrics(((Button)target).getFont());
        String label = ((Button)target).getLabel();
        if (label == null) {
            label = "";
        }
        return new Dimension(fm.stringWidth(label) + 14, fm.getHeight() + 8);
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    public native void setLabel(String label);

    MyWButtonPeer(Button target) {
        super(target);
    }

    @Override
    native void create(MyWComponentPeer peer);

    private static native void initIDs();
}
