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
    /**
     * Метод переопределяющий equals.
     * @return - переопределённый equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        if (x != cell.x) {
            return false;
        }
        return y == cell.y;
    }
    /**
     * Метод переопределяющий hashCode.
     * @return - переопределённый hashCode.
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
