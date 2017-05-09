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
public class StudentTest {
	/**
     * Test проверяющий конструктор.
     */
	@Test
    public void whenSetCourseThreeThenGetCourseThree() {
		Student studCourse = new Student(3);
		int expect = 3;
		assertThat(studCourse.getCourse(), is(expect));
    }
}