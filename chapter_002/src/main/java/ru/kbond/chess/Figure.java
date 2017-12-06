package ru.kbond.chess;

/**
 * Class Figure - используется для описания методов общих для всех фигур.
 * @author kbondarenko
 * @since 05.12.2017
 * @version 1
 */
public abstract class Figure {
    /**
     * Поле хранящее позицию фигуры.
     * @param position - позиция.
     */
    final Cell position;
    /**
     * Конструктор.
     * @param position - позиция фигуры.
     */
    public Figure(Cell position) {
        this.position = position;
    }
    /**
     * Метод проверяющий правильность хода фигуры.
     * @return - список клеток ходов.
     */
    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;
    /**
     * Метод перемещающий фигуру на новое положение.
     * @return - фигура с новыми координатами.
     */
    abstract Figure clone(Cell dest);
}
