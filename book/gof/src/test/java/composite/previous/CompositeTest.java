package composite.previous;

import composite.previous.CompositeGraphic;
import composite.previous.LeafComponent;
import org.junit.jupiter.api.Test;

public class CompositeTest {
    @Test
    public void CompositeTest() {
        LeafComponent leafComponent1 = new LeafComponent();
        LeafComponent leafComponent2 = new LeafComponent();
        LeafComponent leafComponent3 = new LeafComponent();

        CompositeGraphic graphicroot = new CompositeGraphic();

        CompositeGraphic graphic1 = new CompositeGraphic();
        CompositeGraphic graphic2 = new CompositeGraphic();

        graphic1.add(leafComponent1);
        graphic1.add(leafComponent2);

        graphic2.add(leafComponent3);

        graphicroot.add(graphic1);
        graphicroot.add(graphic2);

        graphicroot.print();
    }
}
