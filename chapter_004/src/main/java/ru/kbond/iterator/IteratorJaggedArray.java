package ru.kbond.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class iterator for a two-dimensional array.
 * @author kbondarenko
 * @since 01.02.2018
 * @version 1
 */
public class IteratorJaggedArray implements Iterator<Integer> {
    /**
     * The field storing a two-dimensional array.
     * @param value  two-dimensional array value.
     */
    private final int[][] values;
    /**
     * The field array row index.
     * @param indexRow  row index.
     */
    private int indexRow = 0;
    /**
     * The field array column index.
     * @param indexRow  column index.
     */
    private int indexColumn = 0;
    /**
     * Constructor.
     * @param values  two-dimensional array.
     */
    public IteratorJaggedArray(final int[][] values) {
        this.values = values;
    }
    /**
     * Method returns {@code true} if the iteration has more elements.
     * @return  {@code true} if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {
        pointerPosition();
        return values.length > indexRow;
    }
    /**
     * Method returns the next element in the iteration.
     * @return  the next element in the iteration.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    @Override
    public Integer next() {
        if (values.length == indexRow) {
            throw new NoSuchElementException();
        }
        pointerPosition();
        return values[indexRow][indexColumn++];
    }
    /**
     * Method returns {@code true} if the items in the column have run out.
     * @return  {@code true} if the items in the column have run out.
     */
    public boolean pointerPosition() {
        boolean endRow = false;
        if (values[indexRow].length == indexColumn) {
            indexRow++;
            indexColumn = 0;
            endRow = true;
        }
        return endRow;
    }
}
