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
public class DoctorTest {
	/**
     * Test проверяющий возврат строки.
     */
	@Test
    public void whenSetNameDoctorSetNameVirusThenGetStringExpect() {
		Doctor white = new Doctor("Уолтер");
		Virus flu = new Virus("грипп");
		String expect = "Доктор Уолтер исследует вирус грипп";
		assertThat(white.research(flu), is(expect));
    }
}