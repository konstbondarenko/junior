package ru.kbond.multithreading.storage;

import java.util.Objects;

/**
 * User.
 *
 * @author kbondarenko
 * @since 03.06.2018
 * @version 1
 */
public class User {
    private int id;
    private int amount;
    /**
     * Constructor.
     *
     * @param id  user id.
     * @param amount  user amount.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
    /**
     * Getter.
     */
    public int getId() {
        return id;
    }
    /**
     * Getter.
     */
    public int getAmount() {
        return amount;
    }
    /**
     * Setter.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    /**
     * Equals.
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
        return id == user.id;
    }
    /**
     * HashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * ToString.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", amount="
                + amount + '}';
    }
}
