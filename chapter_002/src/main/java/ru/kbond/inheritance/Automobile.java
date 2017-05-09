package ru.kbond.inheritance;

/**
 * Class Teacher, подкласс-профессия.
 * @author kbondarenko
 * @since 08.05.2017
 * @version 1
 */
public class Automobile {
	/**
	 * Поле хранящее название чертежа.
	 * @param drawingTitleName - имя.
	 */
    private String drawingTitleName;
	/**
	 * Конструктор.
	 * @param drawingTitleName - имя.
	 */
    public Automobile(String drawingTitleName) {
        this.drawingTitleName = drawingTitleName;
    }
	/**
	 * Getter.
	 * @return - результат.
	 */
    public String getDrawingTitleName() {
        return drawingTitleName;
    }
}