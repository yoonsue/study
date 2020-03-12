package templateMethod;

import java.io.Closeable;
import java.io.IOException;
import java.nio.CharBuffer;

abstract class MReader implements Readable, Closeable {

    protected Object lock;

    protected MReader() {
        this.lock = this;
    }

    protected MReader(Object lock) {
        this.lock = lock;
    }

    public int read() throws IOException {
        char cb[] = new char[1];
        if (read(cb, 0, 1) == -1)
            return -1;
        else
            return cb[0];
    }

    public int read(char cbuf[]) throws IOException {
        return read(cbuf, 0, cbuf.length);
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        int len = cb.remaining();
        char[] cbuf = new char[len];
        int n = read(cbuf, 0, len) ;
        if (n > 0)
            cb.put(cbuf, 0, n);
        return n;
    }

    public void reset() throws IOException {
        throw new IOException("reset() not supported");
    }

    public abstract int read(char cbuf[], int off, int len) throws IOException;

    public abstract void close();
}
