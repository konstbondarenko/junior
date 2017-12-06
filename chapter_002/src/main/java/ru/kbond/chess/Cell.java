package ru.kbond.chess;

/**
 * Class Cell - используется для хранения координат фигуры.
 * @author kbondarenko
 * @since 05.12.2017
 * @version 1
 */
public class Cell {
    /**
     * Поле хранящее координату y.
     * @param x - координата.
     */
    int x;
    /**
     * Поле хранящее координату y.
     * @param y - координата.
     */
    int y;
    /**
     * Конструктор.
     * @param x - координата.
     * @param y - координата.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
