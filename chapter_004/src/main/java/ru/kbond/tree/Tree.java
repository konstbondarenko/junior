package ru.kbond.tree;

import java.util.*;

/**
 * Collection on the basis of tree.
 * Does not contain duplicates,
 * keeps the insertion order.
 * An iterator is supported.
 *
 * @author kbondarenko
 * @since 15.03.2018
 * @param <E> data type.
 * @version 1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Tree root.
     */
    private Node<E> root;
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
     * The size of the tree (the number of elements it contains).
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
     * The element of tree.
     */
    private class Node<E extends Comparable<E>> {
        private final List<Node<E>> children = new ArrayList<>();
        private final E value;
        /**
         * Constructor.
         *
         * @param value  object stored in this node.
         */
        public Node(final E value) {
            this.value = value;
        }
        /**
         * Adding children (leaves) to the root.
         *
         * @param child  children (leaves) to the root.
         */
        public void add(Node<E> child) {
            this.children.add(child);
        }
        /**
         * Children (leaves) to the root.
         *
         * @return  List children (leaves) to the root.
         */
        public List<Node<E>> leaves() {
            return this.children;
        }
        /**
         * Compares a value to an equivalence.
         *
         * @param that  compared object.
         * @return  zero if they are equal.
         */
        public boolean eqValue(E that) {
            return this.value.compareTo(that) == 0;
        }
    }
    /**
     * A method for finding a tree node.
     *
     * @param value  the object to be found.
     * @return  if the value is found, the node will be returned.
     */
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }
    /**
     * The method adds a new node to the existing one.
     *
     * @param parent  node to which a new element will be added.
     * @param child  the node to add.
     * @return  {@code true} if the element is not a duplicate.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean checkAdd = false;
        if (parent.compareTo(child) != 0) {
            if (this.root == null) {
                this.root = new Node<>(parent);
                this.size++;
                this.root.add(new Node<>(child));
                this.size++;
                this.modCount++;
                checkAdd = true;
            }
            if (!findBy(child).isPresent()) {
                Optional<Node<E>> result = findBy(parent);
                Node<E> tmp = result.get();
                tmp.add(new Node<>(child));
                this.size++;
                this.modCount++;
            }
        }
        return checkAdd;
    }
    /**
     * Check whether the tree is binary.
     *
     * @return  {@code true} if the tree is binary.
     */
    public boolean isBinary() {
        boolean checkBinary = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() <= 2) {
                for (Node<E> child : el.leaves()) {
                    data.offer(child);
                }
            } else {
                checkBinary = false;
            }
        }
        return checkBinary;
    }
    /**
     * Returns an iterator over the elements in this container in proper sequence.
     *
     * @return an iterator over the elements in this container in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>(this.root);
    }

    /**
     * Iterator.
     */
    private class TreeIterator<E extends Comparable<E>> implements Iterator<E> {
        /**
         * The queue for the root and its nodes.
         */
        private Queue<Node<E>> data = new LinkedList<>();
        /**
         * The root from which iteration begins.
         */
        private Node<E> item;
        /**
         * Constructor.
         * Calls a method listValue to create a list of tree values.
         *
         * @param root  nodes for iteration.
         */
        public TreeIterator(final Node<E> root) {
            this.data.offer(root);
        }
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
            return !this.data.isEmpty();
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
            this.item = this.data.poll();
            for (Node<E> child : this.item.leaves()) {
                this.data.offer(child);
            }
            return this.item.value;
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
