package ru.kbond.collections.tree;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 20.03.2018
 * @version 1
 */
public class BinaryTreeTest {
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNumbersSequentially() {
        BinaryTree<Integer> binary = new BinaryTree<>();
        binary.add(5);
        binary.add(4);
        binary.add(6);
        binary.add(3);
        binary.add(7);

        Iterator<Integer> it = binary.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
    /**
     * Test.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowExceptionWhenModifyingAfterCreatingIterator() {
        BinaryTree<Integer> binary = new BinaryTree<>();
        binary.add(5);

        Iterator<Integer> it = binary.iterator();
        binary.add(6);
        it.next();
    }
}