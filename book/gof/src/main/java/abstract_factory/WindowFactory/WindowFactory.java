package abstract_factory.WindowFactory;

import abstract_factory.Button;
import abstract_factory.GUIFactory;

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
