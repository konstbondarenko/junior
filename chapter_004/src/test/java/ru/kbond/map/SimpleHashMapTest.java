package ru.kbond.map;

import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 11.03.2018
 * @version 1
 */
public class SimpleHashMapTest {
    /**
     * Test.
     */
    @Test
    public void whenAddKeyValuesThanContainsPairs() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");

        assertThat(simpleHashMap.contains(1), is(true));
        assertThat(simpleHashMap.contains(2), is(true));
        assertThat(simpleHashMap.contains(3), is(false));
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddKeyValuesThanKeyValuesWithoutDuplicates() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>(2);

        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");
        simpleHashMap.insert(1, "two");

        Iterator<Integer> it = simpleHashMap.iterator();

        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
        it.next();
    }
    /**
     * Test.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowExceptionWhenModifyingAfterCreatingIterator() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>(2);

        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");

        Iterator<Integer> it = simpleHashMap.iterator();
        simpleHashMap.delete(1);

        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
    }
    /**
     * Test.
     */
    @Test
    public void whenDeleteKeyValuesThanDeletePair() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");
        simpleHashMap.delete(1);

        assertThat(simpleHashMap.contains(1), is(false));
    }
}