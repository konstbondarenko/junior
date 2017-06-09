package ru.kbond.strategy;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 09.06.2017
 * @version 1
 */
public class PaintTest {
    /**
     * Тест, проверяющий создание треугольника.
     */
    @Test
    public void whenPaintTriangleThenTriangle() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.draw(new Triangle());
        System.out.println(paint.executeShape());
        assertThat(out.toString(), is(String.format("  ^%s ^^^%s^^^^^%s",
                System.getProperty("line.separator"),
                System.getProperty("line.separator"),
                System.getProperty("line.separator"))));
    }
    /**
     * Тест, проверяющий создание квадрата.
     */
    @Test
    public void whenPaintSquareThenSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.draw(new Square());
        System.out.println(paint.executeShape());
        assertThat(out.toString(), is(String.format("***%s***%s***%s",
                System.getProperty("line.separator"),
                System.getProperty("line.separator"),
                System.getProperty("line.separator"))));
    }
}
