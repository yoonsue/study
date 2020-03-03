package composite;

public class LeafComponent implements Graphic {
    @Override
    public void print() {
        System.out.println("\tLeaf Component");
    }
}
