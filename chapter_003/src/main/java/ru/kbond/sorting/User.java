package ru.kbond.sorting;

/**
 * Class storing user data and a comparator for sorting by age.
 * @author kbondarenko
 * @since 26.12.2017
 * @version 1
 */
public class User implements Comparable<User> {
    /**
     * The field storing the name user.
     * @param name - user name.
     */
    private String name;
    /**
     * The field in which the user's age is stored.
     * @param age - user age.
     */
    private int age;
    /**
     * Constructor.
     * @param name - user name.
     * @param age - user age.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    /**
     * Getter.
     * @return getAge - user age.
     */
    public int getAge() {
        return age;
    }
    /**
     * Override compareTo(T o) for sorting by age.
     * @return - a negative integer, zero, or a positive integer as this age
     *          is less than, equal to, or greater than the specified age.
     */
    @Override
    public int compareTo(User o) {
        return getAge() == o.getAge() ? 0 : getAge() > o.getAge() ? 1 : -1;
    }
    /**
     * toString.
     * @return - string representation of the object.
     */
    @Override
    public String toString() {
        return "User{"
                +
                "name='" + name + '\''
                +
                ", age=" + age
                +
                '}';
    }
}
