package com.atozmak.circleandroundimageviewdemo.utils;

/**
 * Created by Mak on 2016/3/22.
 */
public class LogUtil {

    public static final String PREFIX = "CircleAnd..._";

    public static String makeLogTag(String string) {
        return PREFIX + string;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }
}
