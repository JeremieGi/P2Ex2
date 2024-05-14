package com.animals.safety.utils;

public class StringUtils {
    public static String capitalize(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
}
