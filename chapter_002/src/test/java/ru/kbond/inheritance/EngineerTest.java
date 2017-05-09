package ru.kbond.inheritance;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 09.05.2017
 * @version 1
 */
public class EngineerTest {
	/**
     * Test проверяющий возврат строки.
     */
    @Test
    public void whenSetNameEngineerSetNameAutomobileThenGetStringExpect() {
        Engineer engn = new Engineer("Сергей");
        Automobile seriesR = new Automobile("rs");
        String expect = "Инженер Сергей разрабатывает rs";
        assertThat(engn.development(seriesR), is(expect));
    }
}