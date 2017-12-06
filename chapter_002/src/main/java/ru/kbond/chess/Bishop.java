package ru.kbond.chess;

/**
 * Class Bishop - используется для описания поведения фигуры Bishop.
 * @author kbondarenko
 * @since 05.12.2017
 * @version 1
 */
public class Bishop extends Figure {
    /**
     * Конструктор.
     * @param position - позиция фигуры.
     */
    public Bishop(Cell position) {
        super(position);
    }
    /**
     * Способ перемещения фигуры.
     * @param diagonal - способ перемещения фигурыр.
     */
    DiagonalWay diagonal = new DiagonalWay();
    /**
     * Метод проверяющий правильность хода фигуры.
     * @return - список клеток ходов.
     */
    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        return diagonal.way(source, dest);
    }
    /**
     * Метод копирующий фигуру с новыми координатами.
     * @return - фигура с новыми координатами.
     */
    @Override
    Figure clone(Cell dest) {
        return new Bishop(dest);
    }
}
