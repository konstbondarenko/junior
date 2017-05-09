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
public class ProfessionTest {
	/**
     * Test провер¤ющий конструктор.
     */
	@Test
    public void whenSetNameDoctorThenGetNameDoctor() {
		Profession prof = new Profession("Doctor");
		String expect = "Doctor";
		assertThat(prof.getName(), is(expect));
    }
}