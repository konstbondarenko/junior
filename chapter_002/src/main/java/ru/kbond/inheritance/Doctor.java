package ru.kbond.inheritance;

/**
 * Class Doctor, подкласс-профессия.
 * @author kbondarenko
 * @since 09.05.2017
 * @version 1
 */
public class Doctor extends Profession {
	/**
	 * Конструктор.
	 * @param name - имя.
	 */
    public Doctor(String name) {
        this.setName(name);
    }
	/**
	 * Метод возвращающий строку.
	 * @param virus - исследуемый вирус.
	 * @return - результат
	 */
    public String research(Virus virus) {
        return "Доктор " + this.getName() + " исследует вирус " + virus.getName();
    }
}