package ru.kbond.tracker;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 05.06.2017
 * @version 1
 */
public class StubInputTest {
    /**
     * Тест, проверяющий добавление объекта через меню.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0",
                "1", "testName", "desc", Arrays.toString(new String[]{"0"}),
                "y"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("testName"));
    }
    /**
     * Тест, проверяющий вывод всех объектов для пользователя.
     */
    @Test
    public void whenUserShowAllItemThenTrackerHasShowAllItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0",
                "1", "testNameFirst", "desc", Arrays.toString(new String[]{"0"}),
                "no",
                "0",
                "2", "testNameSecond", "desc", Arrays.toString(new String[]{"0"}),
                "no",
                "1",
                "y"
        });
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("testNameFirst"));
        assertThat(tracker.findAll()[1].getName(), is("testNameSecond"));
    }
    /**
     * Тест, проверяющий изменения в объекте пользователем.
     */
    @Test
    public void whenUserEditItemThenTrackerHasItemWithUpdateName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0",
                "1", "testNameFirst", "desc", Arrays.toString(new String[]{"0"}),
                "no",
                "2",
                "1", "testNameSecond", "desc", Arrays.toString(new String[]{"0"}),
                "y"
        });
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("testNameSecond"));
    }
    /**
     * Тест, проверяющий удаление объекта пользователем.
     */
    @Test
    public void whenUserDeleteItemThenTrackerHasItemWithDelete() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0",
                "1", "testNameFirst", "desc", Arrays.toString(new String[]{"0"}),
                "no",
                "0",
                "2", "testNameSecond", "desc", Arrays.toString(new String[]{"0"}),
                "no",
                "3",
                "1",
                "y"
        });
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("testNameSecond"));
    }
    /**
     * Тест, проверяющий поиск по id объекта пользователем.
     */
    @Test
    public void whenUserFindByIdItemThenTrackerHasShowItemWithId() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0",
                "1", "testName", "desc", Arrays.toString(new String[]{"0"}),
                "4",
                "1",
                "y"
        });
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll()[0].getId(), is("1"));
    }
    /**
     * Тест, проверяющий поиск по имени объекта пользователем.
     */
    @Test
    public void whenUserFindByIdItemThenTrackerHasShowItemWithI() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{
                "0",
                "1", "testName", "desc", Arrays.toString(new String[]{"0"}),
                "no",
                "5",
                "testName",
                "y"
        });
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("testName"));
    }
}
