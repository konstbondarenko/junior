package ru.kbond.strategy;

/**
 * Class Square - рисует квадрат.
 * @author kbondarenko
 * @since 09.06.2017
 * @version 1
 */
public class Square implements Shape {
    /**
     * Метод рисующий квадрат.
     * @return - результат.
     */
    @Override
    public String pic() {
        StringBuilder square = new StringBuilder();
        square.append("***");
        square.append(System.getProperty("line.separator"));
        square.append("***");
        square.append(System.getProperty("line.separator"));
        square.append("***");
        return square.toString();
    }
}
