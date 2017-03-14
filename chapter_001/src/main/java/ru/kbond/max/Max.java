package ru.kbond.max;

/**
 * Class Max для вычисления максимального числа из 2.
 * @author kbondarenko
 * @since 14.03.2017
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
	/**
	 * Метод возвращающий максимальное из 3 чисел.
	 * @param first первое число.
	 * @param second второе число.
	 * @param third третье число.
	 * @return результат
	 */
	public int maxOfThree(int first, int second, int third) {
		return this.max(first, this.max(second, third));
	}
}