package ru.kbond.inheritance;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 08.05.2017
 * @version 1
 */
public class TeacherTest {
	/**
     * Test проверяющий возврат строки.
     */
	@Test
    public void whenSetNameTeacherSetCourseStudentThenGetStringExpect() {
		Teacher teach = new Teacher("Мария");
		Student studCourse = new Student(3);
		String expect = "Преподаватель Мария проводит лекцию для студентов 3 курса";
		assertThat(teach.teaches(studCourse), is(expect));
    }
}