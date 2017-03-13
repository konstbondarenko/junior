package ru.kbond.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * Test.
 * @author kbondarenko
 * @since 13.03.2017
 * @version 1
 */
public class TriangleTest {
	/**
     * Test a(3,2); b(4,1); c(3,5).
     */
    @Test
    public void whenAddPointsThenBuildTriangle() {
		Point a = new Point(3, 2);
        Point b = new Point(4, 1);
        Point c = new Point(3, 5);
        Triangle triangle = new Triangle(a, b, c);
        assertThat(triangle.area(), is(closeTo(1.5, 0.5)));
    }
	/**
     * Test a(1,2); b(2,3); c(4,5).
	 * Фигура не является треугольником.
     */
	@Test
    public void whenAddPointsThenFailureBuildTriangle() {
        Point a = new Point(1, 2);
        Point b = new Point(2, 3);
        Point c = new Point(4, 5);
        Triangle triangle = new Triangle(a, b, c);
        assertThat(triangle.area(), is(closeTo(0.0, 0.0)));
    }
}