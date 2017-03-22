package ru.kbond.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 22.03.2017
 * @version 1
 */
public class TurnTest {
	/**
     * Тест, проверяющий переворот массива с чётным числом элементов {2, 6, 1, 4}.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
		Turn turn = new Turn();
		int[] resultArray = {2, 6, 1, 4};
		int[] expectArray = {4, 1, 6, 2};
		assertThat(turn.back(resultArray), is(expectArray));
    }
	/**
     * Тест, проверяющий переворот массива с нечётным числом элементов {1, 2, 3, 4, 5}.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
		Turn turn = new Turn();
		int[] resultArray = {1, 2, 3, 4, 5};
		int[] expectArray = {5, 4, 3, 2, 1};
		assertThat(turn.back(resultArray), is(expectArray));
    }
}