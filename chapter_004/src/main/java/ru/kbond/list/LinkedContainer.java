package ru.kbond.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly-linked container.
 * An iterator is supported.
 *
 * @author kbondarenko
 * @since 26.02.2018
 * @version 1
 */
public class LinkedContainer<E> implements Iterable<E>  {
    /**
     * Pointer to first node.
     */
    protected Node<E> first;
    /**
     * Pointer to last node.
     */
    protected Node<E> last;
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
     * The size of the LinkedContainer (the number of elements it contains).
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
     * Appends the specified element to the end of this container.
     *
     * @param e  element to be appended to this container.
     */
    public void add(E e) {
        final Node<E> lastNode = this.last;
        final Node<E> newNode = new Node<>(lastNode, e, null);
        this.last = newNode;
        if (lastNode == null) {
            this.first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
        modCount++;
    }
    /**
     * Returns the element at the specified position in this container.
     *
     * @param index  index of the element to return.
     * @return  the element at the specified position in this container.
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        checkElementIndex(index);
        Node<E> getElement = first;
        for (int i = 0; i < index; i++) {
            getElement = getElement.next;
        }
        return getElement.item;
    }
    /**
     * Removes and returns the first element from this container.
     *
     * @return the element at the front of this container (which is the top
     *         of the stack represented by this container).
     * @throws NoSuchElementException if this container is empty.
     */
    public E removeFirst() {
        final Node<E> remFirst = first;
        if (remFirst == null) {
            throw new NoSuchElementException();
        }
        final E element = remFirst.item;
        final Node<E> next = remFirst.next;
        remFirst.item = null;
        remFirst.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }
    /**
     * Removes and returns the last element from this container.
     *
     * @return the last element from this container.
     * @throws NoSuchElementException if this container is empty.
     */
    public E removeLast() {
        final Node<E> remLast = last;
        if (remLast == null) {
            throw new NoSuchElementException();
        }
        final E element = remLast.item;
        final Node<E> prev = remLast.prev;
        remLast.item = null;
        remLast.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }
    /**
     * The method checks whether the index leaves the container.
     *
     * @param index  index of the element to return.
     * @return  {@code true} if the element at the specified position in this container.
     */
    private boolean checkElementIndex(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("Index: " + index
                    +
                    ", Size: " + size);
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
        return indexOf(o) != -1;
    }
    /**
     * Returns the index of the first occurrence of the specified element
     * in this container, or -1 if this list does not contain the element.
     *
     * @return  the index of the first occurrence of the specified element
     * in this container, or -1 if this container does not contain the element.
     */
    public int indexOf(Object o) {
        int index = 0;
        int count = 0;
        boolean checkIndex = false;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    index = count;
                    checkIndex = true;
                    break;
                }
                count++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    index = count;
                    checkIndex = true;
                    break;
                }
                count++;
            }
        }
        return checkIndex ? index : -1;
    }
    /**
     * The element of the linked container.
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        /**
         * Constructor an empty container with the specified initial capacity.
         *
         * @param prev  link to previous node.
         * @param element  object stored in this node.
         * @param next  the link to the next node.
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    /**
     * Returns an iterator over the elements in this container in proper sequence.
     *
     * @return an iterator over the elements in this container in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator<E>();
    }

    /**
     * Iterator.
     */
    private class LinkedIterator<E> implements Iterator<E> {
        /**
         * The first node of the iterable container.
         *
         * @param head  first node of the iterable container.
         */
        private Node<E> head = (Node<E>) first;
        /**
         * The field element index.
         *
         * @param index  element index.
         */
        private int index;
        /**
         * The modCount value that the iterator believes that the backing
         * container should have. If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        private int expectedModCount = modCount;
        /**
         * Method returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements.
         */
        @Override
        public boolean hasNext() {
            return index < size;
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
            if (index != 0) {
                this.head = head.next;
            }
            this.index++;
            return head.item;
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
