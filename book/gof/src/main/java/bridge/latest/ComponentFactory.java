package bridge.latest;

import java.awt.*;

public interface ComponentFactory {
    default MyButtonPeer createButton(Button target) {
        throw new HeadlessException();
    }
}
