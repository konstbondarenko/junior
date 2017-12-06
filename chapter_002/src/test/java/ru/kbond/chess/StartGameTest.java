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
    public void whenSetTheFigureToThePositionThenMoveTheFigureToANewPosition() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        new StartGame(board).play();
        Figure bishop = new Bishop(new Cell(0,7));
        Cell nextPosition = new Cell(7, 0);
        board.figures[bishop.position.x][bishop.position.y] = bishop;
        board.move(bishop.position, nextPosition);
        bishop = board.figures[nextPosition.x][nextPosition.y];
        assertThat(board.figures[nextPosition.x][nextPosition.y],
                is(bishop));

    }

}
