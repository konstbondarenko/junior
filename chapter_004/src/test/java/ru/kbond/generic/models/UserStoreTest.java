package ru.kbond.generic.models;

import org.junit.Test;
import ru.kbond.generic.SimpleArray;
import ru.kbond.generic.storage.UserStore;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author kbondarenko
 * @since 20.02.2018
 * @version 1
 */
public class UserStoreTest {
    /**
     * Test.
     */
    @Test
    public void whenAddUserAndFindById() {
        UserStore<User> store = new UserStore<>(new SimpleArray<>(5));
        User user = new User("1");
        store.add(user);

        assertThat(store.findById("1"), is(user));
    }
    /**
     * Test.
     */
    @Test
    public void whenAddUserOneThenReplaceUserTwo() {
        UserStore<User> store = new UserStore<>(new SimpleArray<>(5));
        User user1 = new User("1");
        User user2 = new User("2");
        store.add(user1);
        store.replace("1", user2);

        assertThat(store.findById("2"), is(user2));
    }
    /**
     * Test.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteUserOneAndFindByIdThenException() {
        UserStore<User> store = new UserStore<>(new SimpleArray<>(5));
        User user = new User("1");
        store.add(user);
        store.delete("1");
        store.findById("1");
    }
}