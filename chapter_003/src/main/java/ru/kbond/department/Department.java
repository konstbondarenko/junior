package ru.kbond.department;

import java.util.*;

/**
 * Class sort subdivision ascending.
 * @author kbondarenko
 * @since 16.01.2018
 * @version 1
 */
public class Department {
    /**
     * Method sort subdivision ascending.
     * @param sorting the List<> to be sort ascending.
     * @return sort subdivision ascending
     *         with the addition of a line with
     *         the code of the upper subdivision.
     */
    public List<String> sortDepartment(List<String> sorting) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < sorting.size(); i++) {
            String[] tmp = sorting.get(i).split("/");
            for (int j = 0; j < tmp.length; j++) {
                if (j == 0) {
                    result.add(tmp[j]);
                } else {
                    result.add(result.get(result.size() - 1) + "/" + tmp[j]);
                }
            }
        }
        Set<String> deleteDuplicate = new TreeSet<>(result);
        return new ArrayList<>(deleteDuplicate);
    }
}
