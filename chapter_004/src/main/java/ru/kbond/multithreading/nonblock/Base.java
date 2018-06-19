package ru.kbond.multithreading.nonblock;

import java.util.Objects;

/**
 * Base model.
 *
 * @author kbondarenko
 * @version 1
 * @since 19.06.2018
 */
public class Base {
    private final int id;
    private String name;
    /**
     * Model version.
     * Serves to implement a non-blocking algorithm.
     */
    private volatile int version = 0;

    /**
     * Constructor.
     *
     * @param id   id model.
     * @param name name model.
     */
    public Base(final int id, final String name) {
        this.id = id;
        this.name = name;
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
    public String getName() {
        return name;
    }

    /**
     * Getter.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Setter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Model version.
     */
    public void updateVersion() {
        this.version++;
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
        Base base = (Base) o;
        return id == base.id;
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
        return "Base{" + "id=" + id
                +
                ", name='" + name + '\'' + '}';
    }
}
