package ru.kbond.collections.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 16.02.2018
 * @version 1
 */
public class SimpleArrayTest {
    /**
     * Test.
     */
    @Test
    public void whenAddIntegerFourAndSixThanGetIntegerFourAndSix() {
        SimpleArray<Integer> simple = new SimpleArray<>(2);
        simple.add(4);
        simple.add(6);

        assertThat(simple.get(0), is(4));
        assertThat(simple.get(1), is(6));
    }
    /**
     * Test.
     */
    @Test
    public void whenSetIntegerNineThanIntegerNine() {
        SimpleArray<Integer> simple = new SimpleArray<>(1);
        simple.add(4);
        simple.set(0, 9);

        assertThat(simple.get(0), is(9));
    }
    /**
     * Test.
     */
    @Test
    public void whenDeleteIntegerFourThanGetNull() {
        SimpleArray<Integer> simple = new SimpleArray<>(2);
        simple.add(4);
        simple.add(6);
        simple.delete(0);

        assertThat(simple.get(0), is(6));
    }
    /**
     * Test.
     */
    @Test
    public void whenAddStringFourAndSixThanGetStringFourAndSix() {
        SimpleArray<String> simple = new SimpleArray<>(2);
        simple.add("4");
        simple.add("6");

        assertThat(simple.get(0), is("4"));
        assertThat(simple.get(1), is("6"));
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNumbersSequentially() {
        SimpleArray<Integer> simple = new SimpleArray<>(2);
        simple.add(4);
        simple.add(6);

        Iterator<Integer> it = simple.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
    /**
     * Test.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        SimpleArray<Integer> simple = new SimpleArray<>(2);
        simple.add(4);
        simple.add(6);

        Iterator<Integer> it = simple.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }
}