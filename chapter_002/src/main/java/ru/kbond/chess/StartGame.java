package ru.kbond.chess;

/**
 * Class StartGame - используется для запуска игры.
 * @author kbondarenko
 * @since 05.12.2017
 * @version 1
 */
public class StartGame {
    /**
     * Поле хранящее доску для игры.
     * @param board - доска.
     */
    Board board;
    /**
     * Конструктор.
     * @param board - доска для фигур.
     */
    public StartGame(Board board) {
        this.board = board;
    }
    /**
     * Метод проверяющий правильность хода фигуры и перемещающий её в случае выполнения условий.
     * @return - перемещение фигуры.
     */
    public void play() {
        Figure bishop = new Bishop(new Cell(0, 2));
        Cell nextPosition = new Cell(2, 4);
        board.figures[bishop.position.x][bishop.position.y] = bishop;
        try {
            board.move(bishop.position, nextPosition);
        } catch (ImpossibleMoveException e) {
            System.out.println("You can not move like this.");
        } catch (FigureNotFoundException e) {
            System.out.println("The figure was not found.");
        } catch (OccupiedWayException e) {
            System.out.println("On the way there are figures.");
        }
    }
}
