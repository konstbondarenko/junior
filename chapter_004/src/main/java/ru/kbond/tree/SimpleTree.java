package ru.kbond.tree;

/**
 * The interface of the tree.
 *
 * @author kbondarenko
 * @since 15.03.2018
 * @param <E> data type.
 * @version 1
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * The method adds a new node to the existing one.
     *
     * @param parent  node to which a new element will be added.
     * @param child  the node to add.
     * @return  {@code true} if the element is not a duplicate.
     */
    boolean add(E parent, E child);
}
