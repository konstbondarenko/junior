package ru.kbond.tictactoe;

/**
 * The class is responsible for the logic of the game.
 *
 * @author kbondarenko
 * @since 23.05.2018
 * @version 1
 */
public class Logic3T {
    private final Figure3T[][] table;
    private int index = 0;
    /**
     * Constructor.
     *
     * @param table  board for the game.
     */
    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }
    /**
     * The method checks whether there is a winning position for X.
     *
     * @return  <tt>true</tt> is a winning position for X.
     */
    public boolean isWinnerX() {
        return checkWin(true);
    }
    /**
     * The method checks whether there is a winning position for O.
     *
     * @return  <tt>true</tt> is a winning position for O.
     */
    public boolean isWinnerO() {
        return checkWin(false);
    }
    /**
     * The method checks if there is still an option for the move.
     *
     * @return  <tt>true</tt> if there is still an option for the move.
     */
    public boolean hasGap() {
        if (index == 19) {
            index = 0;
        }
        return index++ != 18;
    }

    /**
     * The method determines the winning position for the given shape.
     *
     * @param mark  true or false depending on the shape.
     * @return  <tt>true</tt> if there is a winning position.
     */
    private boolean checkWin(Boolean mark) {
        boolean success = false;
        boolean checkGorizontal = true;
        boolean checkVertical = true;
        boolean checkDiagonalLeft = true;
        boolean checkDiagonalRight = true;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (mark) {
                    if (!table[i][j].hasMarkX()) {
                        checkGorizontal = false;
                        break;
                    }
                } else {
                    if (!table[i][j].hasMarkO()) {
                        checkGorizontal = false;
                        break;
                    }
                }
            }
            if (checkGorizontal) {
                break;
            }
        }
        for (int i = 0; i < table.length; i++) {
            checkVertical = true;
            for (int j = 0; j < table[i].length; j++) {
                if (mark) {
                    if (!table[j][i].hasMarkX()) {
                        checkVertical = false;
                        break;
                    }
                } else {
                    if (!table[j][i].hasMarkO()) {
                        checkVertical = false;
                        break;
                    }
                }
            }
            if (checkVertical) {
                break;
            }
        }
        for (int i = 0, j = 0; i < table.length; i++, j++) {
            if (mark) {
                if (!table[i][j].hasMarkX()) {
                    checkDiagonalLeft = false;
                }
                if (!table[i][table.length - 1 - j].hasMarkX()) {
                    checkDiagonalRight = false;
                }
            } else {
                if (!table[i][j].hasMarkO()) {
                    checkDiagonalLeft = false;
                }
                if (!table[i][table.length - 1 - j].hasMarkO()) {
                    checkDiagonalRight = false;
                }
            }
        }
        if (checkGorizontal
                || checkVertical
                || checkDiagonalLeft
                || checkDiagonalRight) {
            success = true;
        }
        return success;
    }
}
