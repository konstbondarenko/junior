package ru.kbond.collections.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 05.03.2018
 * @version 1
 */
public class HashContainerTest {
    /**
     * Test.
     */
    @Test
    public void whenAddIntegersThanContainsIntegers() {
        HashContainer<Integer> container = new HashContainer<>();
        container.add(0);
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
        container.add(6);
        container.add(7);
        container.add(8);
        container.add(9);
        container.add(10);
        container.add(11);
        container.add(12);
        container.add(13);
        container.add(14);
        container.add(15);

        assertThat(container.contains(0), is(true));
        assertThat(container.contains(15), is(true));
        assertThat(container.contains(16), is(false));
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddIntegersThanIntegersWithoutDuplicates() {
        HashContainer<Integer> container = new HashContainer<>(4);
        container.add(0);
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(3);

        Iterator<Integer> it = container.iterator();

        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        it.next();
    }
    /**
     * Test.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowExceptionWhenModifyingAfterCreatingIterator() {
        HashContainer<Integer> container = new HashContainer<>(4);
        container.add(0);
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(3);

        Iterator<Integer> it = container.iterator();
        container.remove(1);

        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }
}