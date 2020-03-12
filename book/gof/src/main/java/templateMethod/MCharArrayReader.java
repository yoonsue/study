package templateMethod;

import java.io.IOException;

public class MCharArrayReader extends MReader {

    protected char buf[];
    protected int pos;
    protected int count;

    public MCharArrayReader(char[] buf) {
        this.buf = buf;
        this.pos = 0;
        this.count = buf.length;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        synchronized (lock) {
            if ((off < 0) ||
                    (off > cbuf.length) ||
                    (len < 0) ||
                    ((off + len) > cbuf.length) ||
                    ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            }

            if (pos >= count) {
                return -1;
            }

            int avail = count - pos;
            if (len > avail) {
                len = avail;
            }
            if (len <= 0) {
                return 0;
            }
            System.arraycopy(buf, pos, cbuf, off, len);
            pos += len;
            return len;
        }
    }

    @Override
    public void close() {
        synchronized (lock) {
            buf = null;
        }
    }
}
