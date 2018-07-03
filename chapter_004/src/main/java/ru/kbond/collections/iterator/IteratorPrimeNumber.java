package ru.kbond.collections.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class iterator for prime numbers.
 * @author kbondarenko
 * @since 13.02.2018
 * @version 1
 */
public class IteratorPrimeNumber implements Iterator<Integer> {
    /**
     * The field storing array.
     * @param value  array value.
     */
    private final int[] values;
    /**
     * The field prime element index.
     * @param index  prime element index.
     */
    private int index = 0;
    /**
     * Constructor.
     * @param values  array.
     */
    public IteratorPrimeNumber(final int[] values) {
        this.values = values;
    }
    /**
     * Method returns {@code true} if the iteration has more elements.
     * @return  {@code true} if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {
        boolean primeHasNext = false;
        while (index < values.length) {
            if (primeNumber(values[index])) {
                primeHasNext = true;
                break;
            }
            index++;
        }
        return primeHasNext;
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
    /**
     * Method returns {@code true} if the number is prime.
     * @return  {@code true} if the number is prime.
     */
    private boolean primeNumber(int number) {
        boolean prime = false;
        for (int i = 2; i <= number; i++) {
            if ((i == number) || (i > Math.sqrt(number))) {
                prime = true;
                break;
            }
            if (number % i == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }
}

