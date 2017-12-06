package ru.kbond.chess;

/**
 * Interface FigureWay - используется для проверки хода фигур.
 * @author kbondarenko
 * @since 06.12.2017
 * @version 1
 */
public interface FigureWay {
    /**
     * Метод проверяющий правильность хода фигуры.
     * @return - список клеток ходов.
     */
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;
}
