package ru.kbond.department;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class sort subdivision.
 * @author kbondarenko
 * @since 16.01.2018
 * @version 1
 */
public class Department {
    /**
     * Method sort subdivision.
     * @param sorting  the List<> to be sort.
     * @return  sort subdivision with the addition
     *          of a line with the code of the upper subdivision.
     */
    public List<List<String>> sortDepartment(List<String> sorting) {
        List<List<String>> result = new ArrayList<>();
        for (String st : sorting) {
            String[] tmp = st.split("/");
            for (int i = 0; i < tmp.length; i++) {
                    result.add(Arrays.asList(Arrays.copyOf(tmp, i + 1)));
            }
        }
        return result.stream().distinct().collect(Collectors.toList());
    }
}
