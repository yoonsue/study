package factoryMethod;

import javafx.scene.control.Tab;

import javax.accessibility.Accessible;
import java.awt.event.ActionListener;

public class ClassTab extends Tab {
    private HTMLPane details;

    public ClassTab() {
        // Product
        details = new HTMLPane();
        setAccessibleName(details, "DETAILS");
    }

    public static void setAccessibleName(Accessible comp, String name) {
        comp.getAccessibleContext().setAccessibleName(name);
    }

}
