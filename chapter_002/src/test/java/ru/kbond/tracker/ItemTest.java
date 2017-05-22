package ru.kbond.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 22.05.2017
 * @version 1
 */
public class ItemTest {
    /**
     * Тест, проверяющий конструктор.
     */
    @Test
    public void whenCreateItemThenTrackerGetFieldItem() {
        Item item = new Item("5", "testName", "testDescription", 123L, new String[] {"testComment"});
        assertThat(item.getId(), is("5"));
        assertThat(item.getName(), is("testName"));
        assertThat(item.getDesc(), is("testDescription"));
        assertThat(item.getCreated(), is(123L));
        assertThat(item.getComments()[0], is("testComment"));
    }
}
