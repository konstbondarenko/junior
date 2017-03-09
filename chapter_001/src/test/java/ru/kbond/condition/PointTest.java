package ru.kbond.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 09.03.2017
 * @version 1
 */
public class PointTest {
	/**
     * Test Test a = 1, b = 1, x = 2, y = 3.
     */
	@Test
	public void whenAIsOneAndBIsOneThenTrue() {
		int x = 2;
		int y = 3;
        Point point = new Point(x, y);
		int argA = 1;
		int argB = 1;
		boolean expected = true;
        assertThat(point.is(argA, argB), is(expected));
    }
	/**
     * Test Test a = 1, b = 2, x = 1, y = 1.
     */
	@Test
	public void whenAIsOneAndBIsTwoThenFalse() {
		int x = 1;
		int y = 1;
        Point point = new Point(x, y);
		int argA = 1;
		int argB = 2;
		boolean expected = false;
        assertThat(point.is(argA, argB), is(expected));
    }
}