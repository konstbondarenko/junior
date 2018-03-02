package ru.kbond.set;

import ru.kbond.list.LinkedContainer;
import java.util.Iterator;

/**
 * Class set - a container that does not contain duplicate elements.
 * Is based on an linked container and iterator is supported.
 *
 * @param <T> the type of elements maintained by this set.
 *
 * @author kbondarenko
 * @see LinkedSet
 * @since 01.03.2018
 * @version 1
 */
public class LinkedSet<T> implements Iterable<T> {
    /**
     * Field storing container for a set.
     */
    private LinkedContainer<T> container = new LinkedContainer<>();
    /**
     * Appends the specified element to this container
     * if it is not already present.
     *
     * @param t  element to be appended to this container.
     */
    public void add(T t) {
        if (!container.contains(t)) {
            container.add(t);
        }
    }
    /**
     * Returns an iterator over the elements in this container in proper sequence.
     *
     * @return an iterator over the elements in this container in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
