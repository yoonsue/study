package abstract_factory.previous.WindowFactory;

import abstract_factory.previous.Button;
import abstract_factory.previous.GUIFactory;

// Concrete Factory 2
public class WindowFactory implements GUIFactory {

    public WindowFactory() {
    }

    // Factory Method
    public Button CreateButton() {
        System.out.println("Window Factory - CreateButton");
        return new WindowButton();
    }
}
