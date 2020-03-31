//package bridge.latest.windows;
//
//import bridge.latest.MyComponent;
//
//import sun.awt.CausedFocusEvent;
//import sun.awt.RepaintArea;
//import sun.awt.SunToolkit;
//import sun.awt.Win32GraphicsConfig;
//import sun.awt.windows.WComponentPeer;
//import sun.awt.windows.WToolkit;
//import sun.java2d.ScreenUpdateManager;
//import sun.java2d.SurfaceData;
//import sun.java2d.pipe.Region;
//
//import java.awt.*;
//import java.awt.dnd.DropTarget;
//import java.awt.event.PaintEvent;
//import java.awt.image.ColorModel;
//import java.awt.image.ImageObserver;
//import java.awt.image.ImageProducer;
//import java.awt.image.VolatileImage;
//import java.awt.peer.ComponentPeer;
//import java.awt.peer.ContainerPeer;
//
//// ConcreteImplementor
//public abstract class MyWComponentPeer extends WComponentPeer {
//
//    SurfaceData surfaceData;
//
//    private RepaintArea paintArea;
//
//    private int numBackBuffers = 0;
//
//    public void createScreenSurface(boolean isResize) {
//        Win32GraphicsConfig gc = (Win32GraphicsConfig)getGraphicsConfiguration();
//        ScreenUpdateManager mgr = ScreenUpdateManager.getInstance();
//
//        surfaceData = mgr.createScreenSurface(gc, this, numBackBuffers, isResize);
//    }
//
//    MyWComponentPeer() {
//        super();
//        this.paintArea = new RepaintArea();
//        create(getNativeParent());
//        checkCreation();
//
//        createScreenSurface(false);
//        initialize();
//        start();
//    }
//
//    abstract void create(MyWComponentPeer parent);
//
//    MyWComponentPeer getNativeParent() {
//        Container parent = SunToolkit.getNativeContainer((Component) target);
//        return (MyWComponentPeer) WToolkit.targetToPeer(parent);
//    }
//
//    protected void checkCreation() {
//
//    }
//
//    synchronized native void start();
//
//    void initialize() {
//
//    }
//
//    @Override
//    public void addDropTarget(DropTarget dt) {
//
//    }
//
//    @Override
//    public void removeDropTarget(DropTarget dt) {
//
//    }
//
//    @Override
//    public boolean isObscured() {
//        return false;
//    }
//
//    @Override
//    public boolean canDetermineObscurity() {
//        return false;
//    }
//
//    @Override
//    public void setVisible(boolean v) {
//
//    }
//
//    @Override
//    public void setEnabled(boolean e) {
//
//    }
//
//    @Override
//    public void paint(Graphics g) {
//
//    }
//
//    @Override
//    public void print(Graphics g) {
//
//    }
//
//    @Override
//    public void setBounds(int x, int y, int width, int height, int op) {
//
//    }
//
//    @Override
//    public void handleEvent(AWTEvent e) {
//
//    }
//
//    @Override
//    public void coalescePaintEvent(PaintEvent e) {
//
//    }
//
//    @Override
//    public Point getLocationOnScreen() {
//        return null;
//    }
//
//    @Override
//    public Dimension getPreferredSize() {
//        return null;
//    }
//
//    @Override
//    public Dimension getMinimumSize() {
//        return null;
//    }
//
//    @Override
//    public ColorModel getColorModel() {
//        return null;
//    }
//
//    @Override
//    public Graphics getGraphics() {
//        return null;
//    }
//
//    @Override
//    public FontMetrics getFontMetrics(Font font) {
//        return null;
//    }
//
//    @Override
//    public void setForeground(Color c) {
//
//    }
//
//    @Override
//    public void setBackground(Color c) {
//
//    }
//
//    @Override
//    public void setFont(Font f) {
//
//    }
//
//    @Override
//    public void updateCursorImmediately() {
//
//    }
//
//    @Override
//    public boolean requestFocus(Component lightweightChild, boolean temporary, boolean focusedWindowChangeAllowed, long time, CausedFocusEvent.Cause cause) {
//        return false;
//    }
//
//    @Override
//    public boolean isFocusable() {
//        return false;
//    }
//
//    @Override
//    public Image createImage(ImageProducer producer) {
//        return null;
//    }
//
//    @Override
//    public Image createImage(int width, int height) {
//        return null;
//    }
//
//    @Override
//    public VolatileImage createVolatileImage(int width, int height) {
//        return null;
//    }
//
//    @Override
//    public boolean prepareImage(Image img, int w, int h, ImageObserver o) {
//        return false;
//    }
//
//    @Override
//    public int checkImage(Image img, int w, int h, ImageObserver o) {
//        return 0;
//    }
//
//    @Override
//    public GraphicsConfiguration getGraphicsConfiguration() {
//        return null;
//    }
//
//    @Override
//    public boolean handlesWheelScrolling() {
//        return false;
//    }
//
//    @Override
//    public void createBuffers(int numBuffers, BufferCapabilities caps) throws AWTException {
//
//    }
//
//    @Override
//    public Image getBackBuffer() {
//        return null;
//    }
//
//    @Override
//    public void flip(int x1, int y1, int x2, int y2, BufferCapabilities.FlipContents flipAction) {
//
//    }
//
//    @Override
//    public void destroyBuffers() {
//
//    }
//
//    @Override
//    public void reparent(ContainerPeer newContainer) {
//
//    }
//
//    @Override
//    public boolean isReparentSupported() {
//        return false;
//    }
//
//    @Override
//    public void layout() {
//
//    }
//
//    @Override
//    public void applyShape(Region shape) {
//
//    }
//
//    @Override
//    public void setZOrder(ComponentPeer above) {
//
//    }
//
//    @Override
//    public boolean updateGraphicsData(GraphicsConfiguration gc) {
//        return false;
//    }
//}
