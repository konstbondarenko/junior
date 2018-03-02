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
 * @since 25.02.2018
 * @version 1
 */
public class ArrayContainerTest {
    /**
     * Test.
     */
    @Test
    public void whenAddIntegerFourAndSixThanGetIntegerFourAndSix() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
        container.add(1);

        assertThat(container.get(0), is(1));
    }
    /**
     * Test.
     */
    @Test
    public void whenAddElementThanIncreaseCapacity() {
        ArrayContainer<Integer> container = new ArrayContainer<>(1);
        container.add(1);
        container.add(2);
        container.add(3);

        assertThat(container.get(2), is(3));
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNumbersSequentially() {
        ArrayContainer<Integer> container = new ArrayContainer<>();
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
        ArrayContainer<Integer> container = new ArrayContainer<>();
        container.add(1);
        container.add(2);

        Iterator<Integer> it = container.iterator();

        container.add(3);
        it.next();
    }
}