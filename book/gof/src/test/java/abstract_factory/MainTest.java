package abstract_factory;

import abstract_factory.OSXFactory.OSXFactory;
import abstract_factory.WindowFactory.WindowFactory;
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