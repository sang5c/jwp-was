package webserver.http;

import java.util.List;

public class HttpRequest {
    private final RequestLine requestLine;
    private final HttpHeaders httpHeaders;

    public HttpRequest(RequestLine requestLine, HttpHeaders httpHeaders) {
        this.requestLine = requestLine;
        this.httpHeaders = httpHeaders;
    }

    public static HttpRequest fromStrings(List<String> request) {
        return new HttpRequest(
                new RequestLine(request.get(0)),
                new HttpHeaders(request.subList(1, request.size()))
        );
    }
}
