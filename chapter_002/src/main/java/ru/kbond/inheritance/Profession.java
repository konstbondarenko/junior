package ru.kbond.inheritance;

/**
 * Class Profession суперкласс для подклассов профессий.
 * @author kbondarenko
 * @since 09.05.2017
 * @version 1
 */
public class Profession {
	/**
	 * Поле хранящее имя человека либо название.
	 * @param name - имя.
	 */
    private String name;
	/**
	 * Конструктор.
	 */
    public Profession() {
    }
	/**
	 * Конструктор.
	 * @param name - имя.
	 */
    public Profession(String name) {
        this.name = name;
    }
	/**
	 * Setter.
	 * @param name - name.
	 */
	public void setName(String name) {
        this.name = name;
	}
	/**
	 * Getter.
	 * @return - результат.
	 */
    public String getName() {
        return name;
    }
}