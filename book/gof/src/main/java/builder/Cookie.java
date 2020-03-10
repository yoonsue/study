package builder;

import com.sun.deploy.net.HttpRequest;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.net.CookieHandler;
import java.util.Optional;

public class Cookie {

    public void request() throws IOException { // HttpRequest r, MultiExchange<?> e
//        HttpClient client = e.client();
//        Optional<CookieHandler> cookieHandlerOpt = client.cookieHandler();

//        CookieHandler cookieHandler = cookieHandlerOpt.get();
//        Map<String,List<String>> cookies = cookieHandler.get(r.uri(), userheaders);
        Map<String,List<String>> cookies = null;

        HttpHeadersBuilder systemHeadersBuilder = new HttpHeadersBuilder();
        for (Map.Entry<String, List<String>> entry : cookies.entrySet()) {
            final String hdrname = entry.getKey();
            if (!hdrname.equalsIgnoreCase("Cookie")
                && !hdrname.equalsIgnoreCase("Cookie2"))
                continue;
            List<String> values = entry.getValue();
            if (values == null || values.isEmpty()) continue;
            for (String val : values) {
                systemHeadersBuilder.addHeader(hdrname, val);
            }
        }

    }
}
