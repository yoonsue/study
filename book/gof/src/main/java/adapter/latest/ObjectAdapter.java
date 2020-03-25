package adapter.latest;

import java.io.ObjectInputStream;
import java.util.Iterator;

// Iterator --> ObjectAdapter
public class ObjectAdapter implements Iterator {
    private boolean EOF = false;
    private final ObjectInputStream in;

    public ObjectAdapter(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public boolean hasNext() {
        return EOF == false;
    }

    @Override
    public Object next() {
        try {
            return in.readObject();
        } catch (Exception e) {
            EOF = true;
            return null;
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
