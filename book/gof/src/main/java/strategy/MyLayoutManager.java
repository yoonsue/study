package strategy;

import java.awt.*;

// Strategy
public interface MyLayoutManager extends LayoutManager{

    void addLayoutComponent(String name, Component comp);

    void removeLayoutComponent(String name, Component comp);

    Dimension preferredLayoutSize(Container parent);

    Dimension minimumLayoutSize(Container parent);

    void layoutContainer(Container parent);

    void emptyMethod();

}
