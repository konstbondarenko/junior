package ru.kbond.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 15.03.2018
 * @version 1
 */
public class TreeTest {
    /**
     * Test.
     */
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }
    /**
     * Test.
     */
    @Test
    public void whenElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }
    /**
     * Test.
     */
    @Test
    public void whenAddTwoIdenticalNumbersThenNoDuplicates() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(2, 1);

        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNumbersSequentially() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(2, 4);
        tree.add(2, 5);

        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
    /**
     * Test.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowExceptionWhenModifyingAfterCreatingIterator() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);

        Iterator<Integer> it = tree.iterator();
        tree.add(2, 3);
        it.next();
    }
    /**
     * Test.
     */
    @Test
    public void shouldReturnTrueIfTreeIsBinary() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        assertThat(tree.isBinary(), is(true));
    }
    /**
     * Test.
     */
    @Test
    public void shouldReturnFalseIfTreeIsNotBinary() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        assertThat(tree.isBinary(), is(false));
    }
}