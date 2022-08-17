package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HttpHeadersTest {

    private final List<String> headers = List.of(
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: Chrome"
    );

    @DisplayName("문자열 목록을 받아 객체를 생성한다.")
    @Test
    void create() {
        HttpHeaders httpHeaders =  new HttpHeaders(headers);
        assertThat(httpHeaders.get("Accept")).isEqualTo("application/json");
    }

    @DisplayName("생성하지 않은 헤더 키로 값을 조회시 Exception이 발생한다.")
    @Test
    void invalidKey() {
        HttpHeaders httpHeaders =  new HttpHeaders(headers);
        assertThatThrownBy(() -> httpHeaders.get("Host"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
