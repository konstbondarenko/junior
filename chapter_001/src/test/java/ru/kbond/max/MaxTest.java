package ru.kbond.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 08.03.2017
 * @version 1
 */
public class MaxTest {
	/**
     * Test когда first больше second.
     */
	@Test
	public void whenAddTwoMaxOneThenTwo() {
        Max maximum = new Max();
		int first = 2;
		int second = 1;
        assertThat(maximum.max(first, second), is(2));
    }
	/**
     * Test когда second больше first.
     */
	@Test
	public void whenAddOneMaxTwoThenTwo() {
        Max maximum = new Max();
		int first = 1;
		int second = 2;
        assertThat(maximum.max(first, second), is(2));
    }
	/**
     * Test числа равны.
     */
	@Test
	public void whenAddOneMaxOneThenOne() {
        Max maximum = new Max();
		int first = 1;
		int second = 1;
        assertThat(maximum.max(first, second), is(1));
    }
}