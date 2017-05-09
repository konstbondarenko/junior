package ru.kbond.inheritance;

/**
 * Class Engineer, подкласс-профессия.
 * @author kbondarenko
 * @since 09.05.2017
 * @version 1
 */
public class Engineer extends Profession {
	/**
	 * Конструктор.
	 * @param name - имя.
	 */
    public Engineer(String name) {
        this.setName(name);
    }
	/**
	 * Метод возвращающий строку.
	 * @param automobile - разрабатываемый автомобиль.
	 * @return - результат
	 */
    public String development(Automobile automobile) {
        return "Инженер " + this.getName() + " разрабатывает " + automobile.getDrawingTitleName();
    }
}