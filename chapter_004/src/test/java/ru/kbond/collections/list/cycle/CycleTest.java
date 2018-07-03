package ru.kbond.collections.list.cycle;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 27.02.2018
 * @version 1
 */
public class CycleTest {
    /**
     * Test.
     */
    @Test
    public void whenThereIsCycleThanTrue() {
        NodeCycle<Integer> first = new NodeCycle<>(1);
        NodeCycle<Integer> two = new NodeCycle<>(2);
        NodeCycle<Integer> third = new NodeCycle<>(3);
        NodeCycle<Integer> four = new NodeCycle<>(4);

        Cycle<Integer> cycle = new Cycle<>();

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        boolean result = cycle.hasCycle(first);

        assertThat(result, is(true));
    }
    /**
     * Test.
     */
    @Test
    public void whenThereIsNoCycleThanFalse() {
        NodeCycle<Integer> first = new NodeCycle<>(1);
        NodeCycle<Integer> two = new NodeCycle<>(2);
        NodeCycle<Integer> third = new NodeCycle<>(3);
        NodeCycle<Integer> four = new NodeCycle<>(4);

        Cycle<Integer> cycle = new Cycle<>();

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;

        boolean result = cycle.hasCycle(first);

        assertThat(result, is(false));
    }
    /**
     * Test.
     */
    @Test
    public void whenThereIsCycleInTheMiddleThanTrue() {
        NodeCycle<Integer> first = new NodeCycle<>(1);
        NodeCycle<Integer> two = new NodeCycle<>(2);
        NodeCycle<Integer> third = new NodeCycle<>(3);
        NodeCycle<Integer> four = new NodeCycle<>(4);

        Cycle<Integer> cycle = new Cycle<>();

        first.next = two;
        two.next = first;
        third.next = four;
        four.next = null;

        boolean result = cycle.hasCycle(first);

        assertThat(result, is(true));
    }
}