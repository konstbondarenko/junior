package ru.kbond.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 05.12.2017
 * @version 1
 */
public class StartGameTest {
    /**
     * Тест, проверяющий перемещение фигуры.
     */
    @Test
    public void whenSetFigureToPositionThenFigureANewPosition()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Cell source = new Cell(0, 7);
        Cell dest = new Cell(1, 6);
        DiagonalWay diagonal = new DiagonalWay();
        Cell[] result = diagonal.way(source, dest);
        Cell[] expected = new Cell[]{new Cell(1, 6), null, null, null, null, null, null};
        assertThat(result, is(expected));
    }
    /**
     * Тест, проверяющий возможность хода на ошибки.
     */
    @Test
    public void whenErrorCheckingThenTrue()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        new StartGame(board).play();
        Figure bishop = new Bishop(new Cell(0, 7));
        Cell dest = new Cell(1, 6);
        board.figures[bishop.position.x][bishop.position.y] = bishop;
        assertThat(board.move(bishop.position, dest), is(true));
    }
    /**
     * Тест, проверяющий может ли фигура перемещаться по этому пути.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenErrorCheckingThenImpossibleMoveException()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        new StartGame(board).play();
        Figure bishop = new Bishop(new Cell(0, 7));
        Cell dest = new Cell(1, 5);
        board.figures[bishop.position.x][bishop.position.y] = bishop;
        assertThat(board.move(bishop.position, dest), is(new ImpossibleMoveException()));
    }
    /**
     * Тест, проверяющий есть ли фигура в заданной клетке.
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenErrorCheckingThenFigureNotFoundException()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        new StartGame(board).play();
        Figure bishop = new Bishop(new Cell(0, 7));
        Cell source = new Cell(2, 7);
        Cell dest = new Cell(1, 5);
        board.figures[bishop.position.x][bishop.position.y] = bishop;
        assertThat(board.move(source, dest), is(new FigureNotFoundException()));
    }
    /**
     * Тест, проверяющий есть ли препятствия на пути фигуры.
     */
    @Test(expected = OccupiedWayException.class)
    public void whenErrorCheckingThenOccupiedWayException()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        new StartGame(board).play();
        Figure bishopW = new Bishop(new Cell(0, 2));
        Figure bishopB = new Bishop(new Cell(1, 3));
        Cell dest = new Cell(2, 4);
        board.figures[bishopB.position.x][bishopB.position.y] = bishopB;
        board.figures[bishopW.position.x][bishopW.position.y] = bishopW;
        assertThat(board.move(bishopW.position, dest), is(new OccupiedWayException()));
    }
}
