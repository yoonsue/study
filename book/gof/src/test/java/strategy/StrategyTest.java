package strategy;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {

    @Test
    void MyFlowLayoutTest() {
        JFrame frame = new JFrame();

        frame.getContentPane().setLayout((LayoutManager) new MyFlowLayout());
        frame.add(new JLabel("Hello World"));

        frame.setSize(300, 600);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}