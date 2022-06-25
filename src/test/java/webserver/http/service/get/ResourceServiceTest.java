package webserver.http.service.get;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.FileIoUtils;
import webserver.http.Header;
import webserver.http.request.HttpRequest;
import webserver.http.request.RequestLine;
import webserver.http.response.HttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceServiceTest {

    @DisplayName("Resource 요청을 처리할수 있다.")
    @ValueSource(strings = {"index.html", "favicon.ico", "style.css", "script.js"})
    @ParameterizedTest
    void pathMatch(String path) {
        ResourceService resourceService = new ResourceService();
        HttpRequest httpRequest = new HttpRequest(
                RequestLine.parse("GET " + path + " HTTP/1.1"),
                new Header(Collections.emptyMap(), Collections.emptyMap()), null);

        assertThat(resourceService.pathMatch(httpRequest)).isTrue();
    }

    @DisplayName("Resource 파일을 읽을수 있다.")
    @ValueSource(strings = {"/index.html", "/favicon.ico", "/css/styles.css", "/js/scripts.js"})
    @ParameterizedTest
    void read(String path) throws IOException, URISyntaxException {
        ResourceService resourceService = new ResourceService();
        HttpRequest httpRequest = new HttpRequest(
                RequestLine.parse("GET " + path + " HTTP/1.1"),
                new Header(Collections.emptyMap(), Collections.emptyMap()), null);
        HttpResponse httpResponse = new HttpResponse(httpRequest);

        Map<String, String> resourcePath = new HashMap<>();
        resourcePath.put("html", "./templates");
        resourcePath.put("css", "./static");
        resourcePath.put("ico", "./templates");
        resourcePath.put("js", "./static");

        resourceService.doService(httpRequest, httpResponse);

        assertThat(httpResponse.getBody()).isEqualTo(FileIoUtils.loadFileFromClasspath(resourcePath.get(path.substring(path.lastIndexOf(".") + 1)) + path));
    }

    @DisplayName("Css 파일인 경우 Content-Type을 text/css,*/*;q=0.1 로 변경한다.")
    @ValueSource(strings = {"/css/styles.css"})
    @ParameterizedTest
    void readCss(String path) throws IOException, URISyntaxException {
        ResourceService resourceService = new ResourceService();
        HttpRequest httpRequest = new HttpRequest(
                RequestLine.parse("GET " + path + " HTTP/1.1"),
                new Header(Collections.emptyMap(), Collections.emptyMap()), null);
        HttpResponse httpResponse = new HttpResponse(httpRequest);

        Map<String, String> resourcePath = new HashMap<>();
        resourcePath.put("css", "./static");

        resourceService.doService(httpRequest, httpResponse);

        assertThat(httpResponse.toResponseHeader()).contains("Content-Type: text/css,*/*;q=0.1");
    }
}