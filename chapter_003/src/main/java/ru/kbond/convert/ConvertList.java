package ru.kbond.convert;

import java.util.ArrayList;
import java.util.List;

/**
 * Class converts a two-dimensional array into an collection List<> and back.
 * @author kbondarenko
 * @since 19.12.2017
 * @version 1
 */
public class ConvertList {
    /**
     * The method converts a two-dimensional array into an List<>.
     * @param array - accepted array.
     * @return - two-dimensional array converted to List<>.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] x : array) {
            for (int y : x) {
                result.add(y);
            }
        }
        return result;
    }
    /**
     * The method evenly divides List<> by the number of rows of a two-dimensional array.
     * @param list - accepted List<>.
     * @param rows - number of rows of the future array.
     * @return - List<> converted to a two-dimensional array.
     * If the number of elements is not a multiple of the number of rows -
     * fill the remaining values in the array with zeros.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int y = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;
        int[][] result = new int[rows][y];
        int row = 0, column = 0, count = 0;
        for (int[] x : result) {
            for (int yy : x) {
                if (count < list.size()) {
                    result[row][column++] = list.get(count++);
                }
            }
            row++;
            column = 0;
        }
        return result;
    }
}
