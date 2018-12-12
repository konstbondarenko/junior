package ru.kbond.collections.set;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection of type Set based on a hash table.
 * Since there is no permission for collisions,
 * it is possible to replace elements of one cell
 * with another with a matching hash code.
 * An iterator is supported.
 *
 *
 * @author kbondarenko
 * @since 05.03.2018
 * @version 1
 */
public class HashContainer<E> implements Iterable<E> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * The array buffer into which the elements of the HashContainer are stored.
     * The capacity of the HashContainer is the length of this array buffer.
     *
     * @param value  array objects.
     */
    private Object[] values;
    /**
     * Constructor an empty container with an initial capacity of sixteen.
     */
    public HashContainer() {
        this.values = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    /**
     * Constructor an empty container with the specified initial capacity.
     *
     * @param initialCapacity  the initial capacity of the container.
     */
    public HashContainer(int initialCapacity) {
        this.values = new Object[initialCapacity];
    }
    /**
     * The size of the HashContainer (the number of elements it contains).
     */
    private int size;
    /**
     * Returns the number of elements in this container.
     *
     * @return the number of elements in this container.
     */
    public int size() {
        return size;
    }
    /**
     * The number of times this container has been <i>structurally modified</i>.
     * Structural modifications are those that change the size of the
     * container, or otherwise perturb it in such a fashion that iterations in
     * progress may yield incorrect results.
     *
     * <p>This field is used by the iterator implementation
     * returned by the {@code iterator} methods. If the value of
     * this field changes unexpectedly, the iterator
     * will throw a {@code ConcurrentModificationException} in
     * response to the {@code next}. This provides <i>fail-fast</i>
     * behavior, rather than non-deterministic behavior in
     * the face of concurrent modification during iteration.
     *
     * @param modCount  number of collection changes.
     */
    private int modCount = 0;
    /**
     * Increases container capacity when filling.
     */
    private void increaseCapacity() {
        int oldCapacity = this.values.length;
        int newCapacity = oldCapacity * 2;
        reHashing(newCapacity);
    }
    /**
     * Redistributes old items due to the change in the size of the base array.
     *
     * @param newCapacity  the size of the new array.
     */
    private void reHashing(int newCapacity) {
        Object[] newValue = new Object[newCapacity];
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                int h = hash(values[i].hashCode());
                newValue[position(h, newValue.length)] = values[i];
            }
        }
        values = newValue;
    }
    /**
     * Appends the specified element to this container.
     * Important! Does not accept null.
     *
     * @param e  element to be appended to this container.
     */
    public boolean add(E e) {
        boolean checkAdd = false;
        if (e == null) {
            throw new NullPointerException("The object must not be null");
        }
        if (!contains(e)) {
            if (size == values.length) {
                increaseCapacity();
            }
            int h = hash(e.hashCode());
            values[position(h, values.length)] = e;
            this.size++;
            this.modCount++;
            checkAdd = true;
        }
        return checkAdd;
    }
    /**
     * Returns <tt>true</tt> if this container contains the specified element.     *
     *
     * @param e  element whose presence in this container is to be tested.
     * @return <tt>true</tt> if this container contains the specified element.
     */
    public boolean contains(E e) {
        boolean checkContains = false;
        if (e != null) {
            int h = hash(e.hashCode());
            if (values[position(h, values.length)] != null
                    &&
                    values[position(h, values.length)].equals(e)) {
                checkContains = true;
            }
        }
        return checkContains;
    }
    /**
     * Delete the element at the specified position.
     *
     * @param e  element to be deleted.
     */
    public boolean remove(E e) {
        boolean checkRemove = false;
        if (contains(e)) {
            int h = hash(e.hashCode());
            values[position(h, values.length)] = null;
            checkRemove = true;
            this.size--;
            this.modCount++;
        }
        return checkRemove;
    }
    /**
     * Method calculation hash code for element.
     *
     * @param h  hash code element.
     * @return  number to calculate the index
     *         of an element in an array.
     */
    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    /**
     * Determine the position in the array for the element.
     *
     * @param hash  hash element.
     * @param length  length array.
     */
    private int position(int hash, int length) {
        return Math.abs(hash % length);
    }
    /**
     * Returns an iterator over the elements in this container in proper sequence.
     *
     * @return an iterator over the elements in this container in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return new HashContainerIterator<>(values);
    }

    /**
     * Iterator.
     */
    private class HashContainerIterator<E> implements Iterator<E> {
        /**
         * The field storing array.
         *
         * @param objects  array objects.
         */
        private final Object[] objects;
        /**
         * The field element index.
         *
         * @param index  element index.
         */
        private int index = 0;
        /**
         * The modCount value that the iterator believes that the backing
         * container should have. If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        private int expectedModCount = modCount;
        /**
         * Constructor.
         *
         * @param objects array.
         */
        public HashContainerIterator(final Object[] objects) {
            this.objects = objects;
        }
        /**
         * Method returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            return index < objects.length;
        }
        /**
         * Method returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         * @throws NoSuchElementException if the iteration has no more elements.
         * @throws ConcurrentModificationException this exception will be thrown
         *         out if the container undergoes a change after the iterator is created
         */
        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) objects[index++];
        }
        /**
         * The method checks whether the collection was changed during iteration.
         */
        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
