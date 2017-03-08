package ru.kbond.max;

/**
 * Class Max для вычисления максимального числа из 2.
 * @author kbondarenko
 * @since 08.03.2017
 * @version 1
 */
public class Max {
	/**
	 * Метод возвращающий максимальное из 2 чисел.
	 * @param first первое число.
	 * @param second второе число.
	 * @return результат
	 */
	public int max(int first, int second) {
        int max = first > second ? first : second;
        return max;
    }
}