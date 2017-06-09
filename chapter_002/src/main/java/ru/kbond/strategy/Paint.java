package ru.kbond.strategy;

/**
 * Class Paint - реализует шаблон проектирования - стратегию.
 * @author kbondarenko
 * @since 09.06.2017
 * @version 1
 */
public class Paint {
    /**
     * Поле хранящее фигуру.
     * @param shape - фигура.
     */
    private Shape shape;
    /**
     * Метод устанавливающий фигуру.
     * @param shape - принимаемая фигура.
     */
    public void draw(Shape shape) {
        this.shape = shape;
    }
    /**
     * Метод производящий действие с заданной фигурой.
     * @return - возвращаемый результат.
     */
    public String executeShape() {
        return shape.pic();
    }
}


