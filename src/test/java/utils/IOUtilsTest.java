package utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IOUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(IOUtilsTest.class);

    @Test
    public void readData() throws Exception {
        String data = "abcd123";
        StringReader sr = new StringReader(data);
        BufferedReader br = new BufferedReader(sr);

        logger.debug("parse body : {}", IOUtils.readData(br, data.length()));
    }

    @Test
    public void toStringList() throws Exception {
        String data = "abcd123\r\n" +
                "qwer\r\n" +
                "zxcv\r\n";
        StringReader sr = new StringReader(data);
        BufferedReader br = new BufferedReader(sr);

        List<String> strings = IOUtils.toStringList(br);
        assertThat(strings).containsExactly("abcd123", "qwer", "zxcv");
    }
}
