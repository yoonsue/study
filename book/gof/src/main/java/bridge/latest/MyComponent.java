package bridge.latest;

import java.awt.*;
import java.awt.peer.ComponentPeer;

public class MyComponent extends Component {

    transient Container parent;

    transient volatile ComponentPeer peer;
    static final Object LOCK = new AWTTreeLock();
    static class AWTTreeLock {}

    final ComponentFactory getComponentFactory() {
        final Toolkit toolkit = getToolkit();
        if (toolkit instanceof ComponentFactory) {
            return (ComponentFactory) toolkit;
        }
        throw new AWTError("UI components are unsupported by: " + toolkit);
    }

    public void addNotify() {

    }

    public Toolkit getToolkit() {
        return null;
    }

}
