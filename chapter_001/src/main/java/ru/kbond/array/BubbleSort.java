package ru.kbond.array;

/**
 * Class BubbleSort для сортировки массива методом перестановки.
 * @author kbondarenko
 * @since 23.03.2017
 * @version 1
 */
public class BubbleSort {
	/**
	 * Метод использующий алгоритм сортировки пузырьком.
	 * @param array принимаемый массив.
	 * @return результат
	 */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1 + i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }
}