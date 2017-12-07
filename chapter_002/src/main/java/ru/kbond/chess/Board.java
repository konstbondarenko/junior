package ru.kbond.chess;

import java.util.Arrays;

/**
 * Class Board - используется для хранения массива фигур и проверки возможности хода.
 * @author kbondarenko
 * @since 05.12.2017
 * @version 1
 */
public class Board {
    /**
     * Поле хранящее массив фигур используемых как шахматная доска.
     * @param figures - массив фигур.
     */
    Figure[][] figures = new Figure[8][8];
    /**
     * Метод проверяющий правильность хода фигуры.
     * @return - true если ход возможен.
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        if (figures[source.x][source.y] != null) {
            Cell[] cells = figures[source.x][source.y].way(source, dest);
            int cellsLength = 0;
            for (int i = 0; i < cells.length; i++) {
                if (cells[i] != null) {
                    cellsLength = i;
                }
            }
            for (int i = 0; i < cellsLength; i++) {
                if (figures[cells[i].x][cells[i].y] != null) {
                    throw new OccupiedWayException();
                }
            }
        } else {
            throw new FigureNotFoundException();
        }
        figures[dest.x][dest.y] = figures[source.x][source.y].clone(dest);
        figures[source.x][source.y] = null;
        return true;
    }
}
