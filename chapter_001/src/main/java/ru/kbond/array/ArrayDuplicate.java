package ru.kbond.array;

import java.util.Arrays;

/**
 * Class ArrayDuplicate для удаления дубликатов в массиве.
 * @author kbondarenko
 * @since 01.04.2017
 * @version 1
 */
public class ArrayDuplicate {
	/**
	 * Метод для удаления дубликатов в массиве.
	 * @param array принимаемый массив.
	 * @return результат
	 */
    public String[] remove(String[] array) {
        int end = array.length - 1;
        for (int i = 0; i < end; i++) {
            for (int j = 1 + i; j < end + 1; j++) {
                if (array[i].equals(array[j])) {
                    String tmp = array[end];
                    array[end] = array[j];
                    array[j] = tmp;
                    j--;
                    end--;
                }
            }
        }
        return Arrays.copyOf(array, array.length - end);
    }
}