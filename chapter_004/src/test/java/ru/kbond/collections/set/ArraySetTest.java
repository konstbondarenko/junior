package ru.kbond.collections.set;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 01.03.2018
 * @version 1
 */
public class ArraySetTest {
    /**
     * Test.
     */
    @Test
    public void whenAddIntegersThanGetIntegersWithoutDuplicates() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(2);
        set.add(3);

        Iterator<Integer> it = set.iterator();

        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }
}