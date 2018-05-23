package ru.kbond.multithreading.threads;

import javafx.scene.shape.Rectangle;

/**
 * Rectangle move.
 *
 * @author kbondarenko
 * @since 22.05.2018
 * @version 1
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private boolean exit = true;
    /**
     * Constructor.
     */
    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }
    /**
     * The method used to complete the thread.
     */
    public void stop(boolean exit) {
        this.exit = exit;
    }
    /**
     * The method produces a movement of
     * the rectangle in the window.
     */
    @Override
    public void run() {
        while (this.exit) {
            try {
            while (this.rect.getX() != 300) {
                if (!this.exit) {
                    break;
                }
                this.rect.setX(this.rect.getX() + 1);
                Thread.sleep(20);
            }
            while (this.rect.getX() != 0) {
                if (!this.exit) {
                    break;
                }
                this.rect.setX(this.rect.getX() - 1);
                    Thread.sleep(20);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
