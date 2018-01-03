package ru.kbond.sorting;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 * @author kbondarenko
 * @since 26.12.2017
 * @version 1
 */
public class SortUserTest {
    /**
     * Test checking the sorting by age.
     */
    @Test
    public void whenFifteenTenTwentyThenTenFifteenTwenty() {
        List<User> list = Arrays.asList(
                new User("Ivan", 15),
                new User("Victor", 10),
                new User("Alexander", 20));
        SortUser sortUser = new SortUser();
        Set<User> result = sortUser.sort(list);
        Set<User> expected = new TreeSet<>(Arrays.asList(
                new User("Victor", 10),
                new User("Ivan", 15),
                new User("Alexander", 20)));
        assertThat(result, is(expected));
    }
    /**
     * Test checking the sorting by age.
     */
    @Test
    public void whenEighteenSixteenNineThenNineSixteenEighteen() {
        List<User> list = Arrays.asList(
                new User("Ivan", 18),
                new User("Victor", 16),
                new User("Alexander", 9));
        SortUser sortUser = new SortUser();
        Set<User> result = sortUser.sort(list);
        Set<User> expected = new TreeSet<>(Arrays.asList(
                new User("Alexander", 9),
                new User("Victor", 16),
                new User("Ivan", 18)));
        assertThat(result, is(expected));
    }
    /**
     * Test checking the sorting by name length.
     */
    @Test
    public void whenNameAlexanderIvanThenNameIvanAlexander() {
        List<User> list = Arrays.asList(
                new User("Alexander", 25),
                new User("Ivan", 30));
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(list);
        List<User> expected = Arrays.asList(
                new User("Ivan", 30),
                new User("Alexander", 25));
        assertThat(result, is(expected));
    }
    /**
     * Test checking the sorting by name and age.
     */
    @Test
    public void whenSameNameThenSortAge() {
        List<User> list = Arrays.asList(
                new User("Alexander", 25),
                new User("Ivan", 30),
                new User("Alexander", 20),
                new User("Ivan", 25));
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(list);
        List<User> expected = Arrays.asList(
                new User("Alexander", 20),
                new User("Alexander", 25),
                new User("Ivan", 25),
                new User("Ivan", 30));
        assertThat(result, is(expected));
    }
}