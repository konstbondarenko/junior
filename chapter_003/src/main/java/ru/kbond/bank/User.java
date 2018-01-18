package ru.kbond.bank;

import java.util.Objects;

/**
 * Class storing user data and a comparator for sorting by name.
 * @author kbondarenko
 * @since 18.01.2018
 * @version 1
 */
public class User implements Comparable<User> {
    /**
     * The field storing the name user.
     * @param name  user name.
     */
    private final String name;
    /**
     * The field storing the passport user.
     * @param passport  user passport.
     */
    private final String passport;
    /**
     * Constructor.
     * @param name  user name.
     * @param passport  user passport.
     */
    public User(final String name, final String passport) {
        this.name = name;
        this.passport = passport;
    }
    /**
     * Getter.
     * @return getName  user name.
     */
    public String getName() {
        return name;
    }
    /**
     * Method verifies the user's equivalence by passport.
     * @param passport  user passport.
     * @return  true or false.
     */
    public Boolean matchPassport(String passport) {
        boolean match = false;
        if (this.passport.equals(passport)) {
            match = true;
        }
        return match;
    }
    /**
     * Equals.
     * @return  override equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                &&
                Objects.equals(passport, user.passport);
    }
    /**
     * HashCode.
     * @return  override hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
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
                ", passport='" + passport + '\''
                +
                '}';
    }
    /**
     * Override compareTo(T o) for sorting by name.
     * @return - a negative integer, zero, or a positive integer as this name
     *          is less than, equal to, or greater than the specified name.
     */
    @Override
    public int compareTo(User o) {
        return this.getName().compareTo(o.getName());
    }
}
