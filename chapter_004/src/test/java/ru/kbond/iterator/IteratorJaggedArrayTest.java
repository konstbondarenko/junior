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
 * @since 01.02.2018
 * @version 1
 */
public class IteratorJaggedArrayTest {
    /**
     * The field iterator.
     */
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new IteratorJaggedArray(new int[][]{{1}, {3, 4}, {7}});
    }
    /**
     * Test.
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }
    /**
     * Test.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }
    /**
     * Test.
     */
    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }
    /**
     * Test.
     */
     @Test(expected = NoSuchElementException.class)
     public void shouldReturnNumbersSequentially() {
         assertThat(it.hasNext(), is(true));
         assertThat(it.next(), is(1));
         assertThat(it.hasNext(), is(true));
         assertThat(it.next(), is(3));
         assertThat(it.hasNext(), is(true));
         assertThat(it.next(), is(4));
         assertThat(it.hasNext(), is(true));
         assertThat(it.next(), is(7));
         assertThat(it.hasNext(), is(false));
         it.next();
     }
}