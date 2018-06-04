package ru.kbond.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.*;

/**
 * Resizable-array.
 * An iterator is supported.
 *
 * @author kbondarenko
 * @since 22.02.2018
 * @version 1
 */
@ThreadSafe
public class ArrayContainer<E> implements Iterable<E> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * The array buffer into which the elements of the ArrayContainer are stored.
     * The capacity of the ArrayContainer is the length of this array buffer. Any
     * empty ArrayContainer with elementData == DEFAULT_CAPACITY, EMPTY_ELEMENTDATA
     * will be expanded to DEFAULT_CAPACITY when the first element is added.
     *
     * @param container  array objects.
     */
    @GuardedBy("this")
    private Object[] container;
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
     * The size of the SimpleArray (the number of elements it contains).
     */
    private int size;
    /**
     * Returns the number of elements in this container.
     *
     * @return the number of elements in this container.
     */
    public int size() {
        return this.size;
    }
    /**
     * Constructor an empty container with an initial capacity of ten.
     */
    public ArrayContainer() {
        this.container = new Object[DEFAULT_CAPACITY];
    }
    /**
     * Constructor an empty container with the specified initial capacity.
     *
     * @param initialCapacity  the initial capacity of the container.
     * @throws IllegalArgumentException if the specified initial capacity.
     *         is negative
     */
    public ArrayContainer(int initialCapacity) {
        if (initialCapacity > 0) {
            this.container = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.container = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    +
                    initialCapacity);
        }
    }
    /**
     * Increases container capacity when filling.
     */
    private void increaseCapacity() {
        synchronized (this) {
            int oldCapacity = this.container.length;
            int newCapacity = ((oldCapacity * 3) / 2 + 1);
            this.container = Arrays.copyOf(this.container, newCapacity);
        }
    }
    /**
     * Appends the specified element to the end of this container.
     *
     * @param e  element to be appended to this container.
     */
    public void add(E e) {
        synchronized (this) {
            if (this.size == this.container.length - 1) {
                increaseCapacity();
            }
            this.container[this.size++] = e;
            this.modCount++;
        }
    }
    /**
     * Returns the element at the specified position in this container.
     *
     * @param position  index of the element to return.
     * @return  the element at the specified position in this container.
     * @throws  IndexOutOfBoundsException
     */
    public E get(int position) {
        synchronized (this) {
            checkElementIndex(position);
            return (E) this.container[position];
        }
    }
    /**
     * The method checks whether the index leaves the container.
     *
     * @param index  index of the element to return.
     * @return  {@code true} if the element at the specified position in this container.
     */
    private boolean checkElementIndex(int index) {
        if (!(index >= 0 && index < this.size)) {
            throw new IndexOutOfBoundsException("Index: " + index
                    +
                    ", Size: " + this.size);
        }
        return true;
    }
    /**
     * Returns <tt>true</tt> if this container contains the specified element.
     *
     * @param o  element whose presence in this container is to be tested.
     * @return <tt>true</tt> if this container contains the specified element.
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    /**
     * Returns the index of the first occurrence of the specified element
     * in this container, or -1 if this list does not contain the element.
     *
     * @return  the index of the first occurrence of the specified element
     * in this container, or -1 if this container does not contain the element.
     */
    public int indexOf(Object o) {
        synchronized (this) {
            int index = 0;
            boolean checkIndex = false;
            if (o == null) {
                for (int i = 0; i < this.size; i++) {
                    if (this.container[i] == null) {
                        checkIndex = true;
                        index = i;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < this.size; i++) {
                    if (o.equals(this.container[i])) {
                        checkIndex = true;
                        index = i;
                        break;
                    }
                }
            }
            return checkIndex ? index : -1;
        }
    }
    /**
     * Returns an iterator over the elements in this container in proper sequence.
     *
     * @return an iterator over the elements in this container in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        synchronized (this) {
            return new ContainerIterator<>(this.container);
        }
    }

    /**
     * Iterator.
     */
    private class ContainerIterator<E> implements Iterator<E> {
        /**
         * The field storing array.
         *
         * @param objects  array objects.
         */
        @GuardedBy("this")
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
        public ContainerIterator(final Object[] objects) {
            this.objects = objects;
        }
        /**
         * Method returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            return size > this.index;
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
            synchronized (this) {
                checkForComodification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) this.objects[this.index++];
            }
        }
        /**
         * The method checks whether the collection was changed during iteration.
         */
        final void checkForComodification() {
            if (modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
