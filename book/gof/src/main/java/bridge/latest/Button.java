package bridge.latest;

import java.awt.*;

// Abstraction 로 볼 수 있는가?
// 1) 인터페이스를 제공하지 않을 뿐더러,
//     Implementor 에 대한 참조자도 가지고 있지 않다.(내부에서 쓰기에 참조자가 있다고 봐야하는 것인가?)
public class Button extends MyComponent {

    String label;

    public Button() throws HeadlessException {
        this("");
    }

    public Button(String label) throws HeadlessException {
        GraphicsEnvironment.isHeadless();
        this.label = label;
    }

    public void addNotify() {
        synchronized(getTreeLock()) {
            if (peer == null)
                peer = getComponentFactory().createButton(this);
            super.addNotify();
        }
    }

    public void setLabel(String label) {
        boolean testvalid = false;

        synchronized (this) {
            if (label != this.label && (this.label == null || !this.label.equals(label))) {
                this.label = label;
                MyButtonPeer peer = (MyButtonPeer)this.peer;
                if (peer != null) {
                    peer.setLabel(label);
                }
                testvalid = true;
            }
        }

    }
}
