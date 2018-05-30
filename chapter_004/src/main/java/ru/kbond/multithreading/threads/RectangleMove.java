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
    /**
     * Constructor.
     */
    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }
    /**
     * The method produces a movement of
     * the rectangle in the window.
     */
    @Override
    public void run() {
        int position = 1;
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() + position);
            if (this.rect.getX() == 300 || this.rect.getX() == 0) {
                position *= -1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
