package com.atozmak.fisrtlibrarybyatozmak.util;

/**
 * Created by Mak on 2016/3/30.
 */
public class LogUtil {
    public static final String PREFIX = "firstLib..._";

    public static String makeLogTag(String string) {
        return PREFIX + string;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

}
