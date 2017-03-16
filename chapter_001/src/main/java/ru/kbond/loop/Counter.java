package ru.kbond.loop;

/**
 * Class Counter для подсчёта суммы чисел в заданном диапазоне.
 * @author kbondarenko
 * @since 16.03.2017
 * @version 1
 */
public class Counter {
	/**
	 * Метод для подсчёта суммы чисел в заданном диапазоне.
	 * @param start первое число в диапазоне.
	 * @param finish последнее число в диапазоне.
	 * @return результат
	 */
	public int add(int start, int finish) {
	int sum = 0;
        for (; start <= finish; start++) {
            if (start % 2 == 0) {
                sum = start + sum;
            }
        }
        return sum;
	}
}