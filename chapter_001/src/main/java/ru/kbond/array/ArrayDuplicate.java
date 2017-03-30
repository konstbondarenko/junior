package ru.kbond.array;

import java.util.Arrays;

/**
 * Class ArrayDuplicate для удаления дубликатов в массиве.
 * @author kbondarenko
 * @since 31.03.2017
 * @version 1
 */
public class ArrayDuplicate {
	/**
	 * Метод для удаления дубликатов в массиве.
	 * @param array принимаемый массив.
	 * @return результат
	 */
    public String[] remove(String[] array) {
        int count = 0;
        int cut;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1 + i; j < array.length; j++) {
                if (array[i] != null && array[i].equals(array[j])) {
                    count++;
                    array[j] = null;
                }
            }
        }
        cut = array.length - count;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1 + i; j < array.length - 1; j++) {
                if (array[j] == null) {
                    String tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    return Arrays.copyOf(array, cut);
    }
}