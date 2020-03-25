package prototype.latest;

import java.net.URL;
import java.util.Collection;

// Client
public class AsynchronousStorage {
    private Handler protocol;
    public AsynchronousStorage(Handler protocol) {
        this.protocol = protocol;
    }

    void store(Collection data, URL host) {
        byte[] bytes = new byte[128];
        // Prototype
        Handler handler=(Handler)protocol.clone();
        handler.sendData(bytes, host);
    }
}
