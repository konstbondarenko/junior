package ru.kbond.list;

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
 * @since 25.02.2018
 * @version 1
 */
public class ArrayContainerTest {
    private ArrayContainer<Integer> container;
    private Iterator<Integer> it;
    @Before
    public void setUp() {
        this.container = new ArrayContainer<>();
        this.container.add(1);
        this.container.add(2);
        this.container.add(3);
        this.it = this.container.iterator();
    }
    /**
     * Test.
     */
    @Test
    public void whenAddIntegerFourAndSixThanGetIntegerFourAndSix() {
        assertThat(this.container.get(0), is(1));
    }
    /**
     * Test.
     */
    @Test
    public void whenAddElementThanIncreaseCapacity() {
        assertThat(this.container.get(2), is(3));
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
        assertThat(this.it.hasNext(), is(true));
        assertThat(this.it.next(), is(3));
        assertThat(this.it.hasNext(), is(false));
        this.it.next();
    }
    /**
     * Test.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowExceptionWhenModifyingAfterCreatingIterator() {
        this.container.add(4);
        this.it.next();
    }
}