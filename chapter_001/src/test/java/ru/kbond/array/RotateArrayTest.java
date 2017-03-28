package ru.kbond.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 28.03.2017
 * @version 1
 */
public class RotateArrayTest {
	/**
     * Тест, проверяющий поворот массива размером 2 на 2.
     */
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
		RotateArray array = new RotateArray();
		int[][] resultArray = {{1, 2}, {3, 4}};
		int[][] expectArray = {{3, 1}, {4, 2}};
		assertThat(array.rotate(resultArray), is(expectArray));
    }
	/**
     * Тест, проверяющий поворот массива размером 3 на 3.
     */
    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
		RotateArray array = new RotateArray();
		int[][] resultArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] expectArray = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
		assertThat(array.rotate(resultArray), is(expectArray));
    }
}