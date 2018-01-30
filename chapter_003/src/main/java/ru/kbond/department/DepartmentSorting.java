package ru.kbond.department;

import java.util.*;

/**
 * Class sorts subdivision in ascending and decreasing order.
 * @author kbondarenko
 * @since 16.01.2018
 * @version 1
 */
public class DepartmentSorting {
    /**
     * Method sorts subdivision in decreasing order.
     * @param decreasing  the List<> to be compared.
     * @return  sort List<>.
     */
    public List<List<String>> decreasingSort(List<List<String>> decreasing) {
        Comparator<List<String>> compFirstElem = new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return -o1.get(0).compareTo(o2.get(0));
            }
        };
        Comparator<List<String>> compLength = new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return Integer.compare(o1.toString().length(), o2.toString().length());
            }
        };
        Comparator<List<String>> compLexic = new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return -o1.toString().compareTo(o2.toString());
            }
        };
        decreasing.sort(compFirstElem.thenComparing(compLength).thenComparing(compLexic));
        return decreasing;
    }
    /**
     * Method sorts subdivision in ascending order.
     * @param ascending  the List<> to be compared.
     * @return  sort List<>.
     */
    public List<List<String>> ascendingSort(List<List<String>> ascending) {
        Comparator<List<String>> compFirstElem = new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        };
        Comparator<List<String>> compLength = new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                    return Integer.compare(o1.toString().length(), o2.toString().length());
            }
        };
        Comparator<List<String>> compLexic = new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.toString().compareTo(o2.toString());
            }
        };
        ascending.sort(compFirstElem.thenComparing(compLength).thenComparing(compLexic));
        return ascending;
    }
}
