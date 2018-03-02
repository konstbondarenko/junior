package ru.kbond.list;

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
    /**
     * Test.
     */
    @Test
    public void whenAddIntegerOneAndTwoThanGetIntegerOneAndTwo() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        container.add(1);
        container.add(2);

        assertThat(container.get(0), is(1));
        assertThat(container.get(1), is(2));
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNumbersSequentially() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        container.add(1);
        container.add(2);

        Iterator<Integer> it = container.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
    /**
     * Test.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowExceptionWhenModifyingAfterCreatingIterator() {
        LinkedContainer<Integer> container = new LinkedContainer<>();
        container.add(1);
        container.add(2);

        Iterator<Integer> it = container.iterator();

        container.add(3);
        it.next();
    }
}