package ru.kbond.inheritance;

/**
 * Class Teacher, подкласс-профессия.
 * @author kbondarenko
 * @since 09.05.2017
 * @version 1
 */
public class Teacher extends Profession {
	/**
	 * Конструктор.
	 * @param name - имя.
	 */
    public Teacher(String name) {
        this.setName(name);
    }
	/**
	 * Метод возвращающий строку.
	 * @param student - студенты n курса.
	 * @return - результат
	 */
    public String teaches(Student student) {
        return "Преподаватель " + this.getName() + " проводит лекцию для студентов " + student.getCourse() + " курса";
    }
}