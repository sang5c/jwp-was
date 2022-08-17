package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HttpHeaderTest {

    private final String headerStr = "Accept: application/json";

    @DisplayName("문자열을 받아 객체를 생성한다.")
    @Test
    void create() {
        HttpHeader httpHeader = new HttpHeader(headerStr);
        assertThat(httpHeader.getValue()).isEqualTo("application/json");
    }

    @DisplayName("잘못된 문자열을 받으면 Exception이 발생한다.")
    @Test
    void invalidStr() {
        String str = "invalid str";
        assertThatThrownBy(() -> new HttpHeader(str))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("생성한 헤더의 키와 문자열이 동일한지 비교한다.")
    @Test
    void equalsKey() {
        HttpHeader httpHeader = new HttpHeader(headerStr);
        assertThat(httpHeader.equalsKey("Accept")).isTrue();
        assertThat(httpHeader.equalsKey("Content-Type")).isFalse();
    }
}
