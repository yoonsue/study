package prototype.latest;

import java.net.URL;

// Concrete Prototype
public class HTTPHandler implements Handler {
    @Override
    public void sendData(byte[] data, URL hodst) {

    }

    @Override
    public Object clone() {
        return null;
    }
}
