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
public class FactorialTest {
	/**
     * Test проверяющий, что факториал для числа 5 равен 120.
     */
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
		Factorial fact = new Factorial();
		assertThat(fact.calc(5), is(120));
    }
	/**
     * Test проверяющий, что факториал для числа 0 равен 1.
     */
    @Test
    public void whenCalculateFactorialForZeroThenOne() {
		Factorial fact = new Factorial();
		assertThat(fact.calc(0), is(1));
    }
}