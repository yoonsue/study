package AbstractFactory.OSXFactory;

import AbstractFactory.Button;
import AbstractFactory.GUIFactory;

// Concrete Factory 1
public class OSXFactory implements GUIFactory{
    public OSXFactory() {
    }

    public Button CreateButton() {
        System.out.println("OSX Factory - CreateButton");
        return new OSXButton();
    }

}
