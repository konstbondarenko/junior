package ru.kbond.calculator;

/**
 * Class Calculator для решения арефмитических операций + - / *.
 * @author kbondarenko
 * @since 07.03.2017
 * @version 1
 */
public class Calculator {
	/**
	 * Внутреннее поле класса Calculator.
	 * @result результат
	 */
	private double result;
	/**
	 * Сложение.
	 * @param first первый аргумент.
	 * @param second второй аргумент.
	 * @result результат
	 */
	public void add(double first, double second) {
        this.result = first + second;
    }
	/**
	 * Вычитание.
	 * @param first первый аргумент.
	 * @param second второй аргумент.
	 * @result результат
	 */
	public void substruct(double first, double second) {
        this.result = first - second;
    }
	/**
	 * Деление.
	 * @param first первый аргумент.
	 * @param second второй аргумент.
	 * @result результат
	 */
	public void div(double first, double second) {
        this.result = first / second;
    }
	/**
	 * Умножение.
	 * @param first первый аргумент.
	 * @param second второй аргумент.
	 * @result результат
	 */
	public void multiple(double first, double second) {
        this.result = first * second;
    }
	/**
	 * Метод получения данных внутреннего поля класса Calculator.
	 * @return результат
	 */
    public double getResult() {
        return this.result;
    }
}