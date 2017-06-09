package ru.kbond.strategy;

/**
 * Class Triangle - рисует треугольник.
 * @author kbondarenko
 * @since 09.06.2017
 * @version 1
 */
public class Triangle implements Shape {
    /**
     * Метод рисующий треугольник.
     * @return - результат.
     */
    @Override
    public String pic() {
        StringBuilder triangle = new StringBuilder();
        triangle.append("  ^");
        triangle.append(System.getProperty("line.separator"));
        triangle.append(" ^^^");
        triangle.append(System.getProperty("line.separator"));
        triangle.append("^^^^^");
        return triangle.toString();
    }
}
