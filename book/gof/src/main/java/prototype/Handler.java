package prototype;

import java.net.URL;
import java.util.Optional;

// Prototype
public interface Handler extends Cloneable {
    void sendData(byte[] data, URL host);
    Object clone();
}
