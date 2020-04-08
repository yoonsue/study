package strategy;

import java.awt.*;

// ConcreteStrategy
public class MyFlowLayout implements MyLayoutManager {

    public static final int LEFT      = 0;
    public static final int CENTER      = 1;
    public static final int RIGHT      = 2;
    public static final int LEADING      = 3;
    public static final int TRAILING      = 4;

    int align;
    int newAlign;

    int hgap, vgap;

    private boolean alignOnBaseline;

    public MyFlowLayout() {
        this(CENTER, 5, 5);
    }

    public MyFlowLayout(int align, int hgap, int vgap) {
        this.hgap = hgap;
        this.vgap = vgap;
        setAlignment(align);
    }

    public void setAlignment(int align) {
        this.newAlign = align;

        // this.align is used only for serialization compatibility,
        // so set it to a value compatible with the 1.1 version
        // of the class

        switch (align) {
            case LEADING:
                this.align = LEFT;
                break;
            case TRAILING:
                this.align = RIGHT;
                break;
            default:
                this.align = align;
                break;
        }
    }

    public boolean getAlignOnBaseline() {
        return alignOnBaseline;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public void removeLayoutComponent(String name, Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container target) {
        synchronized (target.getTreeLock()) {
            Dimension dim = new Dimension(0, 0);
            int nmembers = target.getComponentCount();
            boolean firstVisibleComponent = true;
            boolean useBaseline = getAlignOnBaseline();
            int maxAscent = 0;
            int maxDescent = 0;

            for (int i = 0 ; i < nmembers ; i++) {
                Component m = target.getComponent(i);
                if (m.isVisible()) {
                    Dimension d = m.getPreferredSize();
                    dim.height = Math.max(dim.height, d.height);
                    if (firstVisibleComponent) {
                        firstVisibleComponent = false;
                    } else {
                        dim.width += hgap;
                    }
                    dim.width += d.width;
                    if (useBaseline) {
                        int baseline = m.getBaseline(d.width, d.height);
                        if (baseline >= 0) {
                            maxAscent = Math.max(maxAscent, baseline);
                            maxDescent = Math.max(maxDescent, d.height - baseline);
                        }
                    }
                }
            }
            if (useBaseline) {
                dim.height = Math.max(maxAscent + maxDescent, dim.height);
            }
            Insets insets = target.getInsets();
            dim.width += insets.left + insets.right + hgap*2;
            dim.height += insets.top + insets.bottom + vgap*2;
            return dim;
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    @Override
    public void layoutContainer(Container parent) {

    }

    @Override
    public void emptyMethod() {

    }
}
