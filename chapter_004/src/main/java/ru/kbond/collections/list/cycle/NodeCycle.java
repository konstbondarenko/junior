package ru.kbond.collections.list.cycle;

/**
 * Linked container.
 *
 * @author kbondarenko
 * @since 27.02.2018
 * @version 1
 */
public class NodeCycle<E> {
    /**
     * Value.
     *
     * @param value object stored in this node.
     */
    private E value;
    /**
     * Next node.
     *
     * @param next the link to the next node.
     */
    protected NodeCycle<E> next;
    /**
     * Constructor.
     *
     * @param value  object stored in this node.
     */
    public NodeCycle(E value) {
        this.value = value;
    }
}
