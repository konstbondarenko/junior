package ru.kbond.iterator;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 13.02.2018
 * @version 1
 */
public class PrimeIteratorTest {
    /**
     * The field iterator.
     */
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new IteratorPrimeNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnPrimeNumbersOnly() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3571));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
    /**
     * Test.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(3571));
    }
    /**
     * Test.
     */
    @Test
    public void shouldReturnFalseCauseThereIsNoAnyPrimeNumber() {
        it = new IteratorPrimeNumber(new int[]{4, 6});
        assertThat("should return false, cause there is no any prime number", it.hasNext(), is(false));
    }
}
