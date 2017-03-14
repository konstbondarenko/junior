package ru.kbond.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 14.03.2017
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
		/**
     * Test когда third больше.
     */
	@Test
	public void whenAddOneMaxTwoOrThreeThenThree() {
        Max maximum = new Max();
		int first = 1;
		int second = 2;
		int third = 3;
        assertThat(maximum.maxOfThree(first, second, third), is(3));
    }
	/**
     * Test когда second больше.
     */
	@Test
	public void whenAddTwoMaxFourOrOneThenFour() {
        Max maximum = new Max();
		int first = 2;
		int second = 4;
		int third = 1;
        assertThat(maximum.maxOfThree(first, second, third), is(4));
    }
	/**
     * Test когда first больше.
     */
	@Test
	public void whenAddSevenMaxFourOrOneThenSeven() {
        Max maximum = new Max();
		int first = 7;
		int second = 4;
		int third = 1;
        assertThat(maximum.maxOfThree(first, second, third), is(7));
    }
	/**
     * Test когда числа равны.
     */
	@Test
	public void whenAddTwoMaxTwoOrTwoThenTwo() {
        Max maximum = new Max();
		int first = 2;
		int second = 2;
		int third = 2;
        assertThat(maximum.maxOfThree(first, second, third), is(2));
    }
}