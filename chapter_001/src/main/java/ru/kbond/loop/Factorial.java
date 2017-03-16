package ru.kbond.loop;

/**
 * Class Factorial вычисляет факториал.
 * @author kbondarenko
 * @since 16.03.2017
 * @version 1
 */
public class Factorial {
	/**
	 * Метод для вычисления факториала числа.
	 * @param n число, факториал которого рассчитываем.
	 * @return результат
	 */
	public int calc(int n) {
		int fac = 1;
		if (n == 0) {
			fac = 1;
			} else {
				for (int i = 1; i <= n; i++) {
					fac = fac * i;
					}
			}
		return fac;
	}
}