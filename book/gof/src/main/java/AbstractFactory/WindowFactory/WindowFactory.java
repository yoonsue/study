package AbstractFactory.WindowFactory;

import AbstractFactory.Button;
import AbstractFactory.GUIFactory;

// Concrete Factory 2
public class WindowFactory implements GUIFactory {

    public WindowFactory() {
    }

    public Button CreateButton() {
        System.out.println("Window Factory - CreateButton");
        return new WindowButton();
    }
}
