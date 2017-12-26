package ru.kbond.sorting;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class returns the TreeSet of users sorted by age in ascending order.
 * @author kbondarenko
 * @since 26.12.2017
 * @version 1
 */
public class SortUser {
    /**
     * The returns the TreeSet of users sorted by age in ascending order.
     * @return - TreeSet of users.
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }
}
