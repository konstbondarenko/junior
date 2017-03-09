package ru.kbond.condition;

/**
 * Class Point описывает точку в системе координат.
 * @author kbondarenko
 * @since 09.03.2017
 * @version 1
 */
public class Point {
	/**
	 * Внутреннее поле класса Point.
	 * Переменная x.
	 */
    private int x;
	/**
	 * Внутреннее поле класса Point.
	 * Переменная y.
	 */
    private int y;
	/**
	 * Конструктор класса Point.
	 * @param x - x координата
	 * @param y - y координата
	 */
    public  Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Метод получения данных внутреннего поля класса Point.
	 * @return результат x координата
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * Метод получения данных внутреннего поля класса Point.
	 * @return результат y координата
	 */
	public int getY() {
		return this.y;
	}
	/**
	 * Метод класса Point определяющий находится ли точка на этой функции.
	 * @param a - a параметр
	 * @param b - b параметр
	 * @return возвращает true если точка находится на этой функции y(x) = a * x + b.
	 */
	public boolean is(int a, int b) {
		return this.y == a * this.x + b;
    }
}