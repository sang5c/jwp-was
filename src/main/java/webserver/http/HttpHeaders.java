package webserver.http;

import java.util.ArrayList;
import java.util.List;

public class HttpHeaders {

    private final List<HttpHeader> headers = new ArrayList<>();

    public HttpHeaders(List<String> headers) {
        headers.forEach(header -> this.headers.add(new HttpHeader(header)));
    }

    public String get(String key) {
        return headers.stream()
                .filter(h -> h.equalsKey(key))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("invalid key: " + key))
                .getValue();
    }
}
