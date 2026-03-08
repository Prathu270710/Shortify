package com.urlshortener.urlshortener.util;

import org.springframework.stereotype.Component;

@Component
public class Base62Util {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = 62;
    private static final int SHORT_CODE_LENGTH = 7;

    public String encode(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62.charAt((int) (id % BASE)));
            id /= BASE;
        }
        while (sb.length() < SHORT_CODE_LENGTH) {
            sb.append(BASE62.charAt(0));
        }
        return sb.reverse().toString();
    }
}