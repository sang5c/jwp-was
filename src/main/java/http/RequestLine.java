package http;

import java.applet.Applet;

public class RequestLine {

    private final HttpMethod method;
    private final String path;
    private final QueryStrings queryStrings;
    private final Protocol protocol;

    public RequestLine(HttpMethod method, PathAndString pathAndString, Protocol protocol) {
        this.method = method;
        this.path = pathAndString.getPath();
        this.queryStrings = new QueryStrings(pathAndString.getQueryStrings());
        this.protocol = protocol;
    }

    public HttpMethod getMapping() {return this.method;}

    public String getPath() { return this.path; }

    public Protocol getProtocol() { return this.protocol;}

    public QueryStrings getQueryString() { return this.queryStrings;}
}