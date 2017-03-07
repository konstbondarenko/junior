package ru.kbond.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 07.03.2017
 * @version 1
 */
public class CalculatorTest {
	/**
     * Test add.
     */
	@Test
	public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
	/**
     * Test substruct.
     */
	@Test
	public void whenAddTwoSubstructOneThenOne() {
        Calculator calc = new Calculator();
        calc.substruct(2D, 1D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }
	/**
     * Test div.
     */
	@Test
	public void whenAddTenDivFiveThenTwo() {
        Calculator calc = new Calculator();
        calc.div(10D, 5D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
	/**
     * Test multiple.
     */
	@Test
	public void whenAddTwoMultiplThreeThenSix() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 3D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }
}