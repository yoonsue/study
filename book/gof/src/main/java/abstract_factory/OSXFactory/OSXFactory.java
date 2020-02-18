package abstract_factory.OSXFactory;

import abstract_factory.Button;
import abstract_factory.GUIFactory;

// Concrete Factory 1
public class OSXFactory implements GUIFactory{
    public OSXFactory() {
    }

    public Button CreateButton() {
        System.out.println("OSX Factory - CreateButton");
        return new OSXButton();
    }

}
