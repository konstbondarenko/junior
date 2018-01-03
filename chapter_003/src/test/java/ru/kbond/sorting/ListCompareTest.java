package ru.kbond.sorting;

import org.junit.Test;
import java.util.Arrays;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 * @author kbondarenko
 * @since 03.01.2018
 * @version 1
 */
public class ListCompareTest {
    /**
     * Test compares 2 identical List<>.
     */
    @Test
    public void whenLeftAndRightEqualsThenZero() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3)
        );
        assertThat(rst, is(0));
    }
    /**
     * Test compares the List<> when the left is less than the right.
     */
    @Test
    public void whenLeftLessRightThenMunis() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1),
                Arrays.asList(1, 2, 3)
        );
        assertThat(rst, is(-1));
    }
    /**
     * Test compares List<> when left is more than right.
     */
    @Test
    public void whenLeftGreatRightThenPlus() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 2),
                Arrays.asList(1, 1)
        );
        assertThat(rst, is(1));
    }
}