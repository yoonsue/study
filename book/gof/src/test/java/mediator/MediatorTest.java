package mediator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URL;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.*;

class MediatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"http://localhost:8088"})
    void myURLConnectionTest(String urlString) throws Exception {

        URL url = new URL(urlString);
        URLConnection uc = url.openConnection();

        MyURLConnection myUc = getConnection(url);
        System.out.println(myUc.url.toString());
    }

    static MyURLConnection getConnection(URL url) {
        return new DummyURLConnection(url);
    }

    static class DummyURLConnection extends MyURLConnection {

        DummyURLConnection(URL url) {
            super(url);
        }

        public void connect() {
            connected = true;
        }
    }


}