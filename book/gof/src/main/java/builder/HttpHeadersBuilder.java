package builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HttpHeadersBuilder {
    private final TreeMap<String, List<String>> headersMap;

    public HttpHeadersBuilder() {
        headersMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public HttpHeadersBuilder structuralCopy() {
        HttpHeadersBuilder builder = new HttpHeadersBuilder();
        for (Map.Entry<String, List<String>> entry : headersMap.entrySet()) {
            List<String> valueCopy = new ArrayList<>((entry.getValue()));
            builder.headersMap.put(entry.getKey(), valueCopy);
        }
        return builder;
    }

    public void addHeader(String name, String value) {
        headersMap.computeIfAbsent(name, k -> new ArrayList<>(1))
                .add(value);
    }

}
