package webserver.http;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpHeaderTest {

    @Test
    void create() {
        String str = "Accept: application/json";
        HttpHeader httpHeader = new HttpHeader(str);
        assertThat(httpHeader.getValue()).isEqualTo("application/json");
    }
}
