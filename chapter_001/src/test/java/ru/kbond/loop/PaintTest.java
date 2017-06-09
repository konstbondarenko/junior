package ru.kbond.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 20.03.2017
 * @version 1
 */
public class PaintTest {
	/**
     * Test проверяющий отрисовку пирамиды с высотой 2.
     */
    @Test
    public void whenPiramidWithHeightTwoThenStringWithTwoRows() {
        Paint paint = new Paint();
        String result = paint.piramid(2);
        String expected = String.format(" ^%s^^^%s",
                System.getProperty("line.separator"),
                System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }
	/**
     * Test проверяющий отрисовку пирамиды с высотой 3.
     */
    @Test
    public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
        Paint paint = new Paint();
        String result = paint.piramid(3);
        String expected = String.format("  ^%s ^^^%s^^^^^%s",
                System.getProperty("line.separator"),
                System.getProperty("line.separator"),
                System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }
}