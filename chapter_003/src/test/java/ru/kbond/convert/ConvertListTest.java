package ru.kbond.convert;


import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 19.12.2017
 * @version 1
 */
public class ConvertListTest {
    /**
     * Test converts a two-dimensional array to a List<>.
     */
    @Test
    public void whenTwoDimensionalArrayThenList() {
        ConvertList convert = new ConvertList();
        int[][] mass = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> result = convert.toList(mass);
        assertThat(result, is(expected));

    }
    /**
     * Test convert List<> to a two-dimensional array.
     */
    @Test
    public void whenListThenTwoDimensionalArray() {
        ConvertList convert = new ConvertList();
        int rows = 3;
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int[][] result = convert.toArray(list, rows);
        int[][] expected = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertThat(result, is(expected));
    }
    /**
     * Test convert List<> to a two-dimensional array.
     * The number of elements is not a multiple of the number of rows.
     */
    @Test
    public void whenThenTwoDimensionalArray() {
        ConvertList convert = new ConvertList();
        int rows = 5;
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        int[][] result = convert.toArray(list, rows);
        int[][] expected = new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}, {0, 0}};
        assertThat(result, is(expected));
    }
}
