package webserver.http;

import utils.StringUtils;

import java.util.Arrays;

public class HttpHeader {

    private static final int VALID_KEY_VALUE_LENGTH = 2;
    private static final String KEY_VALUE_SPLIT_REGEX = ": ";

    private final String key;
    private final String value;

    public HttpHeader(String str) {
        String[] kv = str.split(KEY_VALUE_SPLIT_REGEX);
        validate(kv);
        this.key = kv[0];
        this.value = kv[1];
    }

    private void validate(String[] kv) {
        if (kv.length != VALID_KEY_VALUE_LENGTH || isEmptyKeyOrValue(kv)) {
            throw new IllegalArgumentException(String.format("invalid str [%s]", Arrays.toString(kv)));
        }
    }

    private static boolean isEmptyKeyOrValue(String[] kv) {
        return StringUtils.isEmpty(kv[0]) || StringUtils.isEmpty(kv[1]);
    }

    public String getValue() {
        return value;
    }
}
