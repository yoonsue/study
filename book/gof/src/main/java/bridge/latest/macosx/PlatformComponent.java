package bridge.latest.macosx;

public interface PlatformComponent {
    void initialize(PlatformComponent platformWindow);

    void setBounds(int x, int y, int w, int g);

    void dispose();
}
