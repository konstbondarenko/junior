package ru.kbond.iterator;

import java.util.*;

/**
 * Class iterator for even numbers.
 * @author kbondarenko
 * @since 05.02.2018
 * @version 1
 */
public class IteratorEvenNumber implements Iterator<Integer> {
    /**
     * The field storing array.
     * @param value  array value.
     */
    private final int[] values;
    /**
     * The field even element index.
     * @param index  even element index.
     */
    private int index = 0;
    /**
     * Constructor.
     * @param values  array.
     */
    public IteratorEvenNumber(final int[] values) {
        this.values = values;
    }
    /**
     * Method returns {@code true} if the iteration has more elements.
     * @return  {@code true} if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {
        boolean even = false;
        while (index < values.length) {
            if (values[index] % 2 == 0) {
                even = true;
                break;
            }
            index++;
        }
        return even;
    }
    /**
     * Method returns the next element in the iteration.
     * @return  the next element in the iteration.
     * @throws  NoSuchElementException if the iteration has no more elements.
     */
    @Override
    public Integer next() {
        hasNext();
        if (index == values.length) {
            throw new NoSuchElementException();
        }
        return values[index++];
    }
}
