package ru.kbond.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class iterator of iterators.
 * @author kbondarenko
 * @since 16.02.2018
 * @version 1
 */
public class IteratorOfIterators {
    /**
     * Method returns an iterator that runs through nested iterators.
     * @return  an iterator that runs through nested iterators.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> insertIt = it.next();
            /**
             * Method returns {@code true} if the iteration has more elements.
             * @return  {@code true} if the iteration has more elements.
             */
            @Override
            public boolean hasNext() {
                if (!insertIt.hasNext()) {
                    if (it.hasNext()) {
                        insertIt = it.next();
                    }
                }
                return insertIt.hasNext();
            }
            /**
             * Method returns the next element in the iteration.
             * @return  the next element in the iteration.
             * @throws  NoSuchElementException if the iteration has no more elements.
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return insertIt.next();
            }
        };
    }
}
