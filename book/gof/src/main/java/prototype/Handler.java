package prototype;

import java.net.URL;

// Prototype
public interface Handler extends Cloneable {
    void sendData(byte[] data, URL host);
    Object clone();
}
