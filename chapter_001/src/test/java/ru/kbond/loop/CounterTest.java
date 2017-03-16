package ru.kbond.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 16.03.2017
 * @version 1
 */
public class CounterTest {
	/**
     * Test start = 1, finish = 10.
     */
    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
		Counter count = new Counter();
		int start = 1;
		int finish = 10;
		assertThat(count.add(start, finish), is(30));
    }
}