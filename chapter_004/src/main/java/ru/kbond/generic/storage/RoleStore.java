package ru.kbond.generic.storage;

import ru.kbond.generic.AbstractStore;
import ru.kbond.generic.Base;
import ru.kbond.generic.SimpleArray;

/**
 * Class container for storing objects Role.
 *
 * @author kbondarenko
 * @since 20.02.2018
 * @version 1
 */
public class RoleStore<T extends Base> extends AbstractStore<T> {
    /**
     * Constructor.
     *
     * @param simpleArray  container for storing objects Role.
     */
    public RoleStore(SimpleArray<T> simpleArray) {
        super(simpleArray);
    }
    /**
     * Appends the specified element to the end of this container.
     *
     * @param model  element to be appended to this container.
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(T model) {
        super.add(model);
    }
    /**
     * Replaces the element at the specified position in this container with
     * the specified element.
     *
     * @param id  the element to replace.
     * @param model  element to be appended to this array.
     */
    @Override
    public boolean replace(String id, T model) {
        return super.replace(id, model);
    }
    /**
     * Delete the element at the id.
     *
     * @param id  element to be deleted.
     */
    @Override
    public boolean delete(String id) {
        return super.delete(id);
    }
    /**
     * Returns the element at the specified position in this container.
     *
     * @param id  element to return.
     * @return  the element corresponding to the given id.
     */
    @Override
    public T findById(String id) {
        return super.findById(id);
    }
    /**
     * Returns index to the element at the specified position in this container.
     * If an id is not found, then an exception throws NoSuchElementException.
     *
     * @param id  element to return.
     * @return  the index element corresponding to the given id.
     * @throws IndexOutOfBoundsException
     */
    @Override
    protected int findIndex(String id) {
        return super.findIndex(id);
    }
}
