package composite.previous;

import java.util.ArrayList;
import java.util.List;

public class CompositeGraphic implements Graphic {
    private List<Graphic> mChildGraphics = new ArrayList<Graphic>();
    @Override
    public void print() {
        int count = 0;
        for (Graphic graphic : mChildGraphics) {
            count += 1;
            System.out.printf(" %d\n", count);
            graphic.print();
        }
    }

    public void add(Graphic graphic) {
        mChildGraphics.add(graphic);
    }

    public void remove(Graphic graphic) {
        mChildGraphics.remove(graphic);
    }
}
