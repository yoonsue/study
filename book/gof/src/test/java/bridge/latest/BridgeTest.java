package bridge.latest;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.InputEvent;

// Test를 어떻게 해야할지 모르겠다. add(b)가 왜 안되는것인지,,, 직접만든 클래스라서 Component 로 인식하지 못하는 것인가.
class BridgeTest {
    private static volatile int eventsCount = 0;
    private static volatile boolean failed = false;

    @Test
    public void setLabel(final String string) {

        Frame frame = new Frame();

        try {
            Button b = new Button("Button");
            frame.setBounds(300, 300, 300, 300);
            frame.add(b);
            frame.setVisible(true);

            Robot robot = new Robot();
            robot.waitForIdle();
            robot.mouseMove((int)frame.getLocationOnScreen().getX() + 150,
                    (int)frame.getLocationOnScreen().getY() + 150);

            eventsCount = 0;
            System.out.println("Clicking mouse...");
            for (int i = 0; i < 300 && !failed; i++) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                Thread.sleep(10);
                b.setLabel("Click: " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}