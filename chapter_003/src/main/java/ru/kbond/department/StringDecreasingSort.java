package ru.kbond.department;

import java.util.Comparator;

/**
 * Class sorts subdivision in descending order.
 * @author kbondarenko
 * @since 16.01.2018
 * @version 1
 */
public class StringDecreasingSort implements Comparator<String> {
    /**
     * Method sorts subdivision in descending order.
     * @param o1 the first string to be compared.
     * @param o2 the second string to be compared.
     * @return a negative integer, or a positive integer as the
     *         first argument is greater than, or less than the
     *         second.
     */
    @Override
    public int compare(String o1, String o2) {
        return -o1.compareTo(o2) == -1 ? -1 : o1.compareTo(o2);
    }
}
