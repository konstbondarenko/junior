package ru.kbond.testtask;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 28.04.2017
 * @version 1
 */
public class CheckForSubstringTest {
	/**
     * Тест, проверяющий наличие подстроки в строке.
     */
	@Test
    public void whenInputStringMilkyWayThenOutputSubstringWay() {
		CheckForSubstring check = new CheckForSubstring();
		String inputString = "milkyWay";
		String inputSubString = "Way";
		boolean expect = true;
		assertThat(check.contains(inputString, inputSubString), is(expect));
	}
}