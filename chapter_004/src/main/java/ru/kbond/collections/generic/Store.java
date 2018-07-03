package ru.kbond.collections.generic;

/**
 * Interface container for storing objects.
 *
 * @author kbondarenko
 * @since 20.02.2018
 * @version 1
 */
public interface Store<T extends Base> {
    /**
     * Appends the specified element to the end of this container.
     *
     * @param model  element to be appended to this container.
     */
    void add(T model);
    /**
     * Replaces the element at the specified position in this container with
     * the specified element.
     *
     * @param id  the element to replace.
     * @param model  element to be appended to this array.
     */
    boolean replace(String id, T model);
    /**
     * Delete the element at the id.
     *
     * @param id  element to be deleted.
     */
    boolean delete(String id);
    /**
     * Returns the element at the specified position in this container.
     *
     * @param id  element to return.
     * @return  the element corresponding to the given id.
     */
    T findById(String id);
}
