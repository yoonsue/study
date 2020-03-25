package bridge.latest.macosx;

import bridge.latest.MyButtonPeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ConcreteImplementor
public class LWMyButtonPeer extends LWComponentPeer<Button, JButton> implements MyButtonPeer, ActionListener {

    public LWMyButtonPeer(final Button target, final PlatformComponent platformComponent) {
        super(target, platformComponent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        postEvent(new ActionEvent(getTarget(), ActionEvent.ACTION_PERFORMED, getTarget().getActionCommand(), e.getWhen(), e.getModifiers()));
    }

    @Override
    public void setLabel(final String label) {
        getDelegate().setText(label);
    }

    @Override
    JButton createDelegate() {
//        return new JButtonDelegate();
        return null;
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

}
