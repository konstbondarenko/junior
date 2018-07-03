package ru.kbond.collections.generic;

/**
 * Class for models with methods String getId.
 *
 * @author kbondarenko
 * @since 20.02.2018
 * @version 1
 */
public abstract class Base {
    /**
     * The field storing id.
     * @param id  id.
     */
    private final String id;
    /**
     * Constructor.
     * @param id  id.
     */
    protected Base(final String id) {
        this.id = id;
    }
    /**
     * Getter.
     */
    public String getId() {
        return id;
    }
}