package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestTest {
    private final List<String> strings = List.of(
            "GET / HTTP/1.1",
            "Host: localhost:8080",
            "Connection: keep-alive"
    );

    @DisplayName("문자열 리스트를 받아 객체를 생성한다")
    @Test
    void create() {
        HttpRequest httpRequest = HttpRequest.fromStrings(strings);
        assertThat(httpRequest).isNotNull();
    }
}
