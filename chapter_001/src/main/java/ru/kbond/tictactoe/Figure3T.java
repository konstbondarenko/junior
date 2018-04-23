package ru.kbond.tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * Class Figure3T - responsible for the cell on the field.
 * It contains methods - whether the cell has a cross or a zero or empty.
 *
 * @author kbondarenko
 * @since 23.05.2018
 * @version 1
 */
public class Figure3T extends Rectangle {
    private boolean markX = false;
    private boolean markO = false;
    /**
     * Constructor.
     * When called, the cell is marked as busy O.
     */
    public Figure3T() {
        this(false);
    }
    /**
     * Constructor.
     */
    public Figure3T(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }
    /**
     * The method marks the cell as X or O,
     * depending on the choice.
     *
     * @param markX  if the <tt>true</tt> that the
     *              cell contains X if not, then O.
     */
    public void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }
    /**
     * The method returns a pipe if the pile contains X.
     *
     * @return  <tt>true</tt> if the pile contains X.
     */
    public boolean hasMarkX() {
        return this.markX;
    }
    /**
     * The method returns a pipe if the pile contains O.
     *
     * @return  <tt>true</tt> if the pile contains O.
     */
    public boolean hasMarkO() {
        return this.markO;
    }
}
