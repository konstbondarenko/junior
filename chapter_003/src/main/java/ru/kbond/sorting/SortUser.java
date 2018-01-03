package ru.kbond.sorting;

import java.util.*;

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
    /**
     * The returns List<> sorted by name length.
     * @return - List<> of users.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        return list;
    }
    /**
     * The returns List<> sorted by age if the name is the same.
     * @return - List<> of users.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName()) == 0
                        ?
                        Integer.compare(o1.getAge(), o2.getAge())
                        :
                        o1.getName().compareTo(o2.getName());
            }
        });
        return list;
    }
}
