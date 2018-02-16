package ru.kbond.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class contains various methods for managing arrays
 * (for example: adding, removing, replacing by index).
 * An iterator is supported.
 *
 * @author kbondarenko
 * @since 16.02.2018
 * @version 1
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * The field storing array.
     *
     * @param objects  array objects.
     */
    private Object[] objects;
    /**
     * The size of the SimpleArray (the number of elements it contains).
     */
    private int size;
    /**
     * Returns the number of elements in this array.
     *
     * @return the number of elements in this array.
     */
    public int size() {
        return size;
    }
    /**
     * Constructor.
     *
     * @param size  size array.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }
    /**
     * Appends the specified element to the end of this array.
     *
     * @param value  element to be appended to this array.
     * @throws IndexOutOfBoundsException
     */
    public void add(T value) {
        this.objects[size++] = value;
    }
    /**
     * Replaces the element at the specified position in this array with
     * the specified element.
     *
     * @param position  index of the element to replace.
     * @param value  element to be appended to this array.
     * @throws  IndexOutOfBoundsException
     */
    public void set(int position, T value) {
        if (position < size) {
            this.objects[position] = value;
        } else {
            throw new IndexOutOfBoundsException("Position: " + position
                    +
                    ", Size: " + size);
        }
    }
    /**
     * Delete the element at the specified position.
     * Shifts any subsequent elements to the left.
     *
     * @param position  index of the element to be deleted.
     * @throws  IndexOutOfBoundsException
     */
    public void delete(int position) {
        System.arraycopy(objects,
                position + 1,
                objects, position,
                objects.length - (position + 1));
        objects[size - 1] = null;
        this.size--;
    }
    /**
     * Returns the element at the specified position in this array.
     *
     * @param position  index of the element to return.
     * @return  the element at the specified position in this array.
     * @throws  IndexOutOfBoundsException
     */
    public T get(int position) {
        return (T) this.objects[position];
    }
    /**
     * Returns an iterator over the elements in this array in proper sequence.
     *
     * @return an iterator over the elements in this array in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<T>(objects);
    }

    /**
     * Iterator.
     */
    private class SimpleArrayIterator<T> implements Iterator<T> {
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
         * Constructor.
         *
         * @param objects  array.
         */
        public SimpleArrayIterator(final Object[] objects) {
            this.objects = objects;
        }
        /**
         * Method returns {@code true} if the iteration has more elements.
         * In this implementation, the iteration goes to the null objects,
         * if they are added using the "add" method when using foreach throw,
         * then there is NullPointerException.
         *
         * @return  {@code true} if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            return size > index;
        }
        /**
         * Method returns the next element in the iteration.
         *
         * @return  the next element in the iteration.
         * @throws  NoSuchElementException if the iteration has no more elements.
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) objects[index++];
        }
    }
}
