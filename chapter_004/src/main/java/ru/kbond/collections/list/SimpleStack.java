package ru.kbond.collections.list;

/**
 * Stack container.
 *
 * @author kbondarenko
 * @since 26.02.2018
 * @version 1
 */
public class SimpleStack<T> extends LinkedContainer<T> {
    /**
     * Retrieves and removes the last element of this container.
     *
     * @return last element of this container, or {@code null}
     *         if this container is empty.
     */
    public T poll() {
        return (last == null) ? null : removeLast();
    }
    /**
     * Pushes an item onto the top of this stack.
     * This method is equivalent to {@link #add}.
     *
     * @param value  the item to be pushed onto this stack.
     */
    public void push(T value) {
        add(value);
    }
}
