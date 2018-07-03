package ru.kbond.collections.list;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 27.02.2018
 * @version 1
 */
public class LinkedContainerTest {
    private LinkedContainer<Integer> container;
    private Iterator<Integer> it;
    @Before
    public void setUp() {
        this.container = new LinkedContainer<>();
        this.container.add(1);
        this.container.add(2);
        this.it = this.container.iterator();
    }
    /**
     * Test.
     */
    @Test
    public void whenAddIntegerOneAndTwoThanGetIntegerOneAndTwo() {
        assertThat(this.container.get(0), is(1));
        assertThat(this.container.get(1), is(2));
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNumbersSequentially() {
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(1));
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(2));
        assertThat(this.it.hasNext(), is(false));
        this.it.next();
    }
    /**
     * Test.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowExceptionWhenModifyingAfterCreatingIterator() {
        this.container.add(3);
        this.it.next();
    }
}