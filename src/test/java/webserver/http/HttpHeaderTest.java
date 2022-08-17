package webserver.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HttpHeaderTest {

    @DisplayName("문자열을 받아 객체를 생성한다.")
    @Test
    void create() {
        String str = "Accept: application/json";
        HttpHeader httpHeader = new HttpHeader(str);
        assertThat(httpHeader.getValue()).isEqualTo("application/json");
    }
    
    @DisplayName("잘못된 문자열을 받으면 Exception이 발생한다.")
    @Test
    void invalidStr() {
        String str = "invalid str";
        assertThatThrownBy(() -> new HttpHeader(str))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
