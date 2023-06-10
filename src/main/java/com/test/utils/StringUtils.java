package com.test.utils;

/**
 * The StringUtils class provides utility methods for string manipulation.
 */
public class StringUtils {

    /**
     * Checks if one string contains another string, ignoring case sensitivity.
     *
     * @param s1 the first string to be checked
     * @param s2 the second string to be checked
     * @return true if the first string contains the second string (ignoring case), false otherwise
     */
    public static boolean containsIgnoreCase(String s1, String s2) {
        return s1.toLowerCase().contains(s2.toLowerCase());
    }
}

