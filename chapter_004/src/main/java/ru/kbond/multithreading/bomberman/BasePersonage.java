package ru.kbond.multithreading.bomberman;

/**
 * The class describes the basic behavior of the character.
 *
 * @author kbondarenko
 * @version 1
 * @since 25.06.2018
 */
public class BasePersonage implements Personage {
    private final String name;
    private final Board board;
    private int x;
    private int y;

    /**
     * Constructor.
     *
     * @param name  character `s name.
     * @param board field where the game takes place.
     */
    public BasePersonage(final String name, final Board board) {
        this.name = name;
        this.board = board;
    }

    /**
     * Getter.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter.
     */
    public int getY() {
        return y;
    }

    /**
     * Getter.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * The method produces random numbers for
     * character movement.
     */
    public void automaticMove() throws InterruptedException {
        int xMove;
        int yMove;
        boolean done = true;
        do {
            xMove = 0;
            yMove = 0;
            int number = (int) (Math.random() * (5));
            if (number == 0) {
                xMove = -1;
            } else if (number == 1) {
                yMove = -1;
            } else if (number == 2) {
                xMove = +1;
            } else if (number == 3) {
                yMove = +1;
            }
            if (checkBorders(this.getX() + xMove, this.getY() + yMove)) {
                if (this.board.move(
                        this.board.getBoardCell()[this.getX()][this.getY()],
                        this.board.getBoardCell()[this.getX() + xMove][this.getY() + yMove])
                        ) {
                    done = false;
                }
            }
        } while (done);
        this.setX(this.getX() + xMove);
        this.setY(this.getY() + yMove);
    }

    /**
     * The method checks the coordinates for the admissibility.
     *
     * @return {@code true} if the coordinates are allowed.
     */
    public boolean checkBorders(int x, int y) {
        boolean check = true;
        if (x >= this.board.getBoardCell().length || y >= this.board.getBoardCell().length) {
            check = false;
        }
        if (x < 0 || y < 0) {
            check = false;
        }
        return check;
    }

    /**
     * The method produces character settings
     * on the field and blocking the cell.
     */
    public void setCharacter() {
        boolean cellLock = false;
        int xTmp;
        int yTmp;
        do {
            xTmp = (int) (Math.random() * (this.board.getBoardCell().length - 1));
            yTmp = (int) (Math.random() * (this.board.getBoardCell().length - 1));
            cellLock = this.board.getBoardCell()[xTmp][yTmp].tryLock();
            if (cellLock) {
                this.setX(xTmp);
                this.setY(yTmp);
            }
        } while (!cellLock);
    }
}
