package ru.kbond.inheritance;

/**
 * Class Student.
 * @author kbondarenko
 * @since 09.05.2017
 * @version 1
 */
public class Student {
	/**
	 * Поле хранящее номер курса.
	 * @param course - курс.
	 */
    private int course;
	/**
	 * Конструктор.
	 * @param course - курс.
	 */
    public Student(int course) {
        this.course = course;
    }
	/**
	 * Getter.
	 * @return - результат.
	 */
    public int getCourse() {
        return course;
    }
}