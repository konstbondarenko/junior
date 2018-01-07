package ru.kbond.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Class comparison of two lists by the sum of numbers.
 * @author kbondarenko
 * @since 03.01.2018
 * @version 1
 */
public class ListCompare implements Comparator<List<Integer>> {
    /**
     * Method compares 2 lists.
     * @param left the first List<> to be compared.
     * @param right the second List<> to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     */
    @Override
    public int compare(List<Integer> left, List<Integer> right) {
        int sumLeft = 0;
        int sumRight = 0;
        boolean result = false;
        if (left.size() == right.size()) {
            for (int i : left) {
                sumLeft = i + sumLeft;
            }
            for (int j : right) {
                sumRight = j + sumRight;
            }
            result = true;
        }
        return result
                ?
                Integer.compare(sumLeft, sumRight)
                :
                Integer.compare(left.size(), right.size());
    }
}