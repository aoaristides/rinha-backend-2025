package br.com.makersweb.rinhabackend2025.domain.utils;

import java.util.UUID;

/**
 * @author aaristides
 */
public final class IdUtils {

    private IdUtils() {}

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase();
    }

}
