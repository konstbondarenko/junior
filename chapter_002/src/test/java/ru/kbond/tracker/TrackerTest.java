package ru.kbond.tracker;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 22.05.2017
 * @version 1
 */
public class TrackerTest {
    /**
     * Тест, проверяющий метод возвращения массива без null.
     */
    @Test
    public void whenFindAllItemThenTrackerHasShowWithoutNullItem() {
        Tracker tracker = new Tracker();
        Item itemfilledFirst = new Item("5", "testNameFirst", "testDescriptionFirst", 123L, new String[] {"testCommentFirst"});
        Item itemNull = null;
        Item itemfilledSecond = new Item("6", "testNameSecond", "testDescriptionSecond", 123L, new String[] {"testCommentSecond"});
        tracker.add(itemfilledFirst);
        tracker.add(itemNull);
        tracker.add(itemfilledSecond);
        assertThat(tracker.findAll()[0], is(itemfilledFirst));
        assertThat(tracker.findAll()[1], is(itemfilledSecond));
    }
    /**
     * Тест, проверяющий добавление элемента в массив.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("5", "testName", "testDescription", 123L, new String[] {"testComment"});
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }
    /**
     * Тест, проверяющий обновление по id элемента в массиве.
     */
    @Test
    public void whenUpdateItemThenTrackerHasRenewedItem() {
        Tracker tracker = new Tracker();
        Item itemLast = new Item("5", "testName", "testDescription", 123L, new String[] {"testComment"});
        Item itemNew = new Item("5", "testNameRenewed", "testDescriptionRenewed", 321L, new String[] {"testCommentRenewed"});
        tracker.add(itemLast);
        tracker.update(itemNew);
        assertThat(tracker.findAll()[0], is(itemNew));
    }
    /**
     * Тест, проверяющий удаление по id элемента в массиве.
     */
    @Test
    public void whenDeleteItemThenTrackerHasDeleteItem() {
        Tracker tracker = new Tracker();
        Item itemDelete = new Item("5", "testNameFirst", "testDescriptionFirst", 123L, new String[] {"testCommentFirst"});
        Item itemShifted = new Item("6", "testNameSecond", "testDescriptionSecond", 123L, new String[] {"testCommentSecond"});
        tracker.add(itemDelete);
        tracker.add(itemShifted);
        tracker.delete("5");
        assertThat(tracker.findAll()[0], is(itemShifted));
    }
    /**
     * Тест, проверяющий поиск по имени элемента в массиве.
     */
    @Test
    public void whenFindByNameItemThenTrackerHasAdjustedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("5", "testName", "testDescription", 123L, new String[] {"testComment"});
        tracker.add(item);
        assertThat(tracker.findByName("testName")[0], is(item));
    }
    /**
     * Тест, проверяющий поиск по id элемента в массиве.
     */
    @Test
    public void whenFindByIdItemThenTrackerHasAdjustedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("5", "testName", "testDescription", 123L, new String[] {"testComment"});
        tracker.add(item);
        assertThat(tracker.findById("5"), is(item));
        assertEquals(tracker.findById("6"), null);
    }
}
