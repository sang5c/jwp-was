package webserver.http;

public class HttpHeader {

    private final String key;
    private final String value;

    public HttpHeader(String str) {
        String[] kv = str.split(": ");
        this.key = kv[0];
        this.value = kv[1];
    }

    public String getValue() {
        return value;
    }
}
