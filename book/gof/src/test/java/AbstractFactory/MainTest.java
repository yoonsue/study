package AbstractFactory;

import AbstractFactory.OSXFactory.OSXFactory;
import AbstractFactory.WindowFactory.WindowFactory;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    public void testOSXFactory() {
        GUIFactory factory;
        factory = new OSXFactory();
        factory.CreateButton();
    }

    @Test
    public void testWindowFactory() {
        GUIFactory factory;
        factory = new WindowFactory();
        factory.CreateButton();
    }
}