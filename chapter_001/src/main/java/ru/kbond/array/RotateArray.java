package ru.kbond.array;

/**
 * Class RotateArray для поворота квадратного массива.
 * @author kbondarenko
 * @since 28.03.2017
 * @version 1
 */
public class RotateArray {
	/**
	 * Метод для поворота квадратного массива.
	 * @param array принимаемый массив.
	 * @return результат
	 */
	public int[][] rotate(int[][] array) {
        int nLen = array.length;
        for (int i = 0; i < nLen / 2; i++) {
            for (int j = 0; j < nLen - 1 - i; j++) {
                int tmp = array[i][j];
                array[i][j] = array[nLen - j - 1][i];
                array[nLen - j - 1][i] = array[nLen - i - 1][nLen - j - 1];
                array[nLen - i - 1][nLen - j - 1] = array[j][nLen - i - 1];
                array[j][nLen - i - 1] = tmp;
            }
		}
		return array;
	}
}