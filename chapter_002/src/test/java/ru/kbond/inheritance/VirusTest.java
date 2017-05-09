package ru.kbond.inheritance;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 08.05.2017
 * @version 1
 */
public class VirusTest {
	/**
     * Test проверяющий конструктор.
     */
	@Test
    public void whenSetNameFluThenGetNameFlu() {
		Virus flu = new Virus("Flu");
		String expect = "Flu";
		assertThat(flu.getName(), is(expect));
    }
}