package ru.kbond.department;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 * @author kbondarenko
 * @since 17.01.2018
 * @version 1
 */
public class DepartmentTest {
    /**
     * Test sort subdivision ascending
     * with the addition of a line with
     * the code of the upper subdivision.
     */
    @Test
    public void whenUnsortedListThenSortedListAddUpperSubDivision() {
        Department department = new Department();
        List<String> list = Arrays.asList(
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2");
        List<String> expected = Arrays.asList(
                "K1",
                "K1/SK1",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K1/SK2",
                "K2",
                "K2/SK1",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2");
        List<String> result = department.sortDepartment(list);
        assertThat(result, is(expected));
    }
    /**
     * Test sort subdivision in descending order
     * with the addition of a line with
     * the code of the upper subdivision.
     */
    @Test
    public void whenUnsortedListThenSortedListDescending() {
        Department department = new Department();
        StringDecreasingSort sorting = new StringDecreasingSort();
        List<String> list = Arrays.asList(
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2");
        List<String> expected = Arrays.asList(
                "K2",
                "K2/SK1",
                "K2/SK1/SSK2",
                "K2/SK1/SSK1",
                "K1",
                "K1/SK2",
                "K1/SK1",
                "K1/SK1/SSK2",
                "K1/SK1/SSK1");
        List<String> result = department.sortDepartment(list);
        result.sort(sorting);
        assertThat(result, is(expected));
    }

}