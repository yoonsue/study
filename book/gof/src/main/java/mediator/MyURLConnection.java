package mediator;

import sun.net.www.MessageHeader;
import sun.security.util.SecurityConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.UnknownServiceException;
import java.security.Permission;

public abstract class MyURLConnection {

    protected URL url;

    protected boolean doInput = true;
    protected boolean doOutput = false;

    protected boolean connected = false;

    private MessageHeader requests;

    protected MyURLConnection(URL url) {
        this.url = url;
    }

    public abstract void connect() throws IOException;

    public void setRequestProperty(String key, String value) {
        checkConnected();
        if (key == null)
            throw new NullPointerException ("key is null");

        if (requests == null)
            requests = new MessageHeader();

        requests.set(key, value);
    }

    private void checkConnected() {
        if (connected)
            throw new IllegalStateException("Already connected");
    }

    public Permission getPermission() throws IOException {
        return SecurityConstants.ALL_PERMISSION;
    }

    public OutputStream getOutputStream() throws  IOException {
        throw new UnknownServiceException("protocol doesn't support output");
    }

    private static int readBytes(int c[], int len, InputStream is)
            throws IOException {

        byte buf[] = new byte[len];
        if (is.read(buf, 0, len) < len) {
            return -1;
        }

        // fill the passed in int array
        for (int i = 0; i < len; i++) {
            c[i] = buf[i] & 0xff;
        }
        return 0;
    }

}
