package abstract_factory.previous;

import abstract_factory.previous.GUIFactory;
import abstract_factory.previous.OSXFactory.OSXFactory;
import abstract_factory.previous.WindowFactory.WindowFactory;
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