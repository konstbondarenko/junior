package ru.kbond.tree;

import java.util.*;

/**
 * Collection on the basis of BinaryTree.
 * An iterator is supported.
 *
 * @author kbondarenko
 * @since 20.03.2018
 * @param <E> data type.
 * @version 1
 */
public class BinaryTree<E extends Comparable<E>> implements Iterable<E> {
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
     * The method adds a new node to the existing one.
     *
     * @param e  node to which a new element will be added.
     */
    public void add(E e) {
        if (this.root == null) {
            this.root = new Node<>(null, e, null);
            this.size++;
            this.modCount++;
        } else {
            insertPoint(this.root, e);
        }
    }
    /**
     * Method that adds a recursively new node.
     *
     * @param node  parent node.
     * @param e  new element will be added.
     */
    private void insertPoint(Node<E> node, E e) {
        if (e.compareTo(node.item) <= 0) {
            if (node.left == null) {
                node.left = new Node<>(null, e, null);
                this.size++;
                this.modCount++;
            } else {
                insertPoint(node.left, e);
            }
        } else if (e.compareTo(node.item) > 0) {
            if (node.right == null) {
                node.right = new Node<>(null, e, null);
                this.size++;
                this.modCount++;
            } else {
                insertPoint(node.right, e);
            }
        }
    }
    /**
     * The element of tree.
     */
    private static class Node<E> {
        E item;
        Node<E> left;
        Node<E> right;
        /**
         * Constructor an empty container with the specified initial capacity.
         *
         * @param left  link to the left child node.
         * @param element  object stored in this node.
         * @param right  link to right child node.
         */
        Node(Node<E> left, E element, Node<E> right) {
            this.left = left;
            this.item = element;
            this.right = right;
        }
    }
    /**
     * Returns an iterator over the elements in this container in proper sequence.
     *
     * @return an iterator over the elements in this container in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return new BinaryTreeIterator<>(this.root);
    }

    /**
     * Iterator.
     */
    private class BinaryTreeIterator<E> implements Iterator<E> {
        /**
         * Stores the value of nodes.
         */
        private List<E> result = new ArrayList<>();
        /**
         * The field element index.
         *
         * @param index  element position.
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
         * Calls a method listItem to create a list of tree values.
         *
         * @param tree  nodes for iteration.
         * @throws NullPointerException
         *         if the tree for iteration contains the null root.
         */
        public BinaryTreeIterator(Node<E> tree) {
            if (tree != null) {
                listItem(tree);
            } else {
                throw new NullPointerException("The root must not be null");
            }
        }
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
         *         out if the container undergoes a change after the iterator is created.
         */
        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return result.get(index++);
        }
        /**
         * Adds the item of nodes to a List.
         */
        private void listItem(Node<E> tree) {
            result.add(tree.item);
            Node<E> r = tree;
            Node<E> l = tree;
            while (index < size - 1) {
                if (l.left != null) {
                    l = l.left;
                    result.add(l.item);
                    index++;
                }
                if (r.right != null) {
                    r = r.right;
                    result.add(r.item);
                    index++;
                }
            }
            index = 0;
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
