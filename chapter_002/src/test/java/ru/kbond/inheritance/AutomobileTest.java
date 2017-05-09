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
public class AutomobileTest {
	/**
     * Test проверяющий конструктор.
     */
    @Test
    public void whenSetNameRsThenGetNameRs() {
        Automobile seriesR = new Automobile("rs");
        String expect = "rs";
        assertThat(seriesR.getDrawingTitleName(), is(expect));
    }
}