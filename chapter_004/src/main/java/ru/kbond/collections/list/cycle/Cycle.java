package ru.kbond.collections.list.cycle;

/**
 * Class determining that the container contains closures.
 *
 * @author kbondarenko
 * @since 27.02.2018
 * @version 1
 */
public class Cycle<E> {
    /**
     * Method determining that the container contains closures.
     * Tortoise and hare pointers to nodes. The hare goes one step
     * ahead of the turtle until they meet or the end of the list is null.
     *
     * @param first  the first node of the container being checked.
     * @return  {@code true} if there is a cycle or {@code false}
     *          if there are no cycles.
     */
    boolean hasCycle(NodeCycle<E> first) {
        NodeCycle tortoise = first;
        NodeCycle hare = first;
        boolean cycle = false;
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;

            if (tortoise == hare) {
                cycle = true;
                break;
            }
        }
        return cycle;
    }
}
