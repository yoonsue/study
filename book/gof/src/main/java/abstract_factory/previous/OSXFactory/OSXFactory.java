package abstract_factory.previous.OSXFactory;

import abstract_factory.previous.Button;
import abstract_factory.previous.GUIFactory;

// Concrete Factory 1
public class OSXFactory implements GUIFactory{
    public OSXFactory() {
    }

    // Factory Method
    public Button CreateButton() {
        System.out.println("OSX Factory - CreateButton");
        return new OSXButton();
    }

}
