package bridge.latest;

import java.awt.peer.ButtonPeer;

// Implementor
public interface MyButtonPeer extends ButtonPeer {

    void setLabel(String label);
}
