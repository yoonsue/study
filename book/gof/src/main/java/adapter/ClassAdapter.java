package adapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;

// Class Adapter
public class ClassAdapter extends ObjectInputStream implements Iterator {
    private boolean EOF = false;

    public ClassAdapter(InputStream in) throws IOException {
        super(in);
    }

    @Override
    public boolean hasNext() {
        return EOF == false;
    }

    @Override
    public Object next() {
        try {
            return readObject();
        } catch (Exception e) {
            EOF = true;
            return null;
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
