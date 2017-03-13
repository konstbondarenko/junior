package ru.kbond.condition;

/**
 * Class Triangle вычисляет площадь треугольника.
 * @author kbondarenko
 * @since 13.03.2017
 * @version 1
 */
public class Triangle {
	/**
	 * Внутреннее поле класса Triangle.
	 * Точка a.
	 */
	private Point a;
	/**
	 * Внутреннее поле класса Triangle.
	 * Точка b.
	 */
	private Point b;
	/**
	 * Внутреннее поле класса Triangle.
	 * Точка c.
	 */
	private Point c;
	/**
	 * Конструктор класса Triangle.
	 * @param a - Point a
	 * @param b - Point b
	 * @param c - Point c
	 */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	 * Метод класса Triangle вычисляющий площадь треугольника.
	 * @return возвращает площадь треугольника либо ошибку.
	 */
	public double area() {
		double triangleArea = Math.abs((b.getX() - a.getX()) * (c.getY() - a.getY()) - (c.getX() - a.getX()) * (b.getY() - a.getY())) / 2;
		if (triangleArea == 0) {
            System.out.println("Ошибка. Вершины совпадают или лежат на одной прямой.");
            return 0;
        }
        return triangleArea;
	}
}