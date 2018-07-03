package ru.kbond.collections.list;

/**
 * Queue container.
 *
 * @author kbondarenko
 * @since 26.02.2018
 * @version 1
 */
public class SimpleQueue<T> extends LinkedContainer<T> {
    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty.
     */
    public T poll() {
        return (first == null) ? null : removeFirst();

    }
    /**
     * Pushes an element onto the queue represented by this container. In other
     * words, inserts the element at the front of this container.
     *
     * <p>This method is equivalent to {@link #add}.
     *
     * @param value  the element to push.
     */
    public void push(T value) {
        add(value);
    }
}
