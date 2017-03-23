package ru.kbond.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 23.03.2017
 * @version 1
 */
public class BubbleSortTest {
	/**
     * Тест, проверяющий сортировку массива из 10 элементов методом пузырька, {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
		BubbleSort bubble = new BubbleSort();
		int[] resultArray = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
	int[] expectArray = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
		assertThat(bubble.sort(resultArray), is(expectArray));
    }
}