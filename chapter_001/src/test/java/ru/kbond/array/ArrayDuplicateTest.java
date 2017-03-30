package ru.kbond.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 31.03.2017
 * @version 1
 */
public class ArrayDuplicateTest {
	/**
     * Тест, проверяющий удаление дубликатов строк из массива строк.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
		ArrayDuplicate duplicate = new ArrayDuplicate();
		String[] resultArray = {"Привет", "Мир", "Привет", "Супер", "Мир"};
		String[] expectArray = {"Привет", "Мир", "Супер"};
		assertThat(duplicate.remove(resultArray), is(expectArray));
    }
}