package ru.kbond.collections.generic;

import java.util.NoSuchElementException;

/**
 * Class container for storing objects.
 *
 * @author kbondarenko
 * @since 20.02.2018
 * @version 1
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * The field container for storing objects.
     *
     * @param simpleArray  container for storing objects.
     */
    private final SimpleArray<T> simpleArray;
    /**
     * Constructor.
     *
     * @param simpleArray  container for storing objects.
     */
    public AbstractStore(final SimpleArray<T> simpleArray) {
        this.simpleArray = simpleArray;
    }
    /**
     * Appends the specified element to the end of this container.
     *
     * @param model  element to be appended to this container.
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(T model) {
        simpleArray.add(model);
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
        int index = findIndex(id);
        simpleArray.set(index, model);
        return true;
    }
    /**
     * Delete the element at the id.
     *
     * @param id  element to be deleted.
     */
    @Override
    public boolean delete(String id) {
        int index = findIndex(id);
        simpleArray.delete(index);
        return true;
    }
    /**
     * Returns the element at the specified position in this container.
     *
     * @param id  element to return.
     * @return  the element corresponding to the given id.
     */
    @Override
    public T findById(String id) {
        int index = findIndex(id);
        return simpleArray.get(index);
    }
    /**
     * Returns index to the element at the specified position in this container.
     * If an id is not found, then an exception throws NoSuchElementException.
     *
     * @param id  element to return.
     * @return  the index element corresponding to the given id.
     * @throws IndexOutOfBoundsException
     * @throws NoSuchElementException
     */
    protected int findIndex(String id) {
        int index = 0;
        boolean checkFind = false;
        for (T t : simpleArray) {
            if (t != null && t.getId().equals(id)) {
                checkFind = true;
                break;
            }
            index++;
        }
        if (!checkFind) {
            throw new NoSuchElementException("Id not found");
        }
        return index;
    }
}
