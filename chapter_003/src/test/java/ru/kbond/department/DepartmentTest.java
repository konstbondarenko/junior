package ru.kbond.department;

import org.junit.Test;
import java.util.ArrayList;
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
    public void whenUnsortedListThenSortedAscendingListAddUpperSubDivision() {
        Department department = new Department();
        DepartmentSorting departmentSorting = new DepartmentSorting();


        List<String> list = Arrays.asList(
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2");

        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("K1"));
        expected.add(Arrays.asList("K1", "SK1"));
        expected.add(Arrays.asList("K1", "SK2"));
        expected.add(Arrays.asList("K1", "SK1", "SSK1"));
        expected.add(Arrays.asList("K1", "SK1", "SSK2"));
        expected.add(Arrays.asList("K2"));
        expected.add(Arrays.asList("K2", "SK1"));
        expected.add(Arrays.asList("K2", "SK1", "SSK1"));
        expected.add(Arrays.asList("K2", "SK1", "SSK2"));

        List<List<String>> result = department.sortDepartment(list);
        departmentSorting.ascendingSort(result);


        assertThat(result, is(expected));
    }
    /**
     * Test sort subdivision in decreasing order
     * with the addition of a line with
     * the code of the upper subdivision.
     */
    @Test
    public void whenUnsortedListThenSortedDecreasingListAddUpperSubDivision() {
        Department department = new Department();
        DepartmentSorting departmentSorting = new DepartmentSorting();


        List<String> list = Arrays.asList(
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2");

        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("K2"));
        expected.add(Arrays.asList("K2", "SK1"));
        expected.add(Arrays.asList("K2", "SK1", "SSK2"));
        expected.add(Arrays.asList("K2", "SK1", "SSK1"));
        expected.add(Arrays.asList("K1"));
        expected.add(Arrays.asList("K1", "SK2"));
        expected.add(Arrays.asList("K1", "SK1"));
        expected.add(Arrays.asList("K1", "SK1", "SSK2"));
        expected.add(Arrays.asList("K1", "SK1", "SSK1"));

        List<List<String>> result = department.sortDepartment(list);
        departmentSorting.decreasingSort(result);


        assertThat(result, is(expected));
    }

}