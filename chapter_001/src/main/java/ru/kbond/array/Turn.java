package ru.kbond.array;

/**
 * Class Turn для переворота массива.
 * @author kbondarenko
 * @since 22.03.2017
 * @version 1
 */
public class Turn {
	/**
	 * Метод для для переворота массива.
	 * @param array принимаемый массив.
	 * @return результат
	 */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
            System.out.println();
        }
        return array;
    }
}