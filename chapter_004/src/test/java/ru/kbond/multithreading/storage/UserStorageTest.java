package ru.kbond.multithreading.storage;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

/**
 * Test.
 *
 * @author kbondarenko
 * @since 03.06.2018
 * @version 1
 */
public class UserStorageTest {
    private UserStorage storage;
    private User alex;
    private User max;
    @Before
    public void setUp() {
        this.storage = new UserStorage();
        this.alex = new User(1, 100);
        this.max = new User(2, 50);
        this.storage.add(this.alex);
        this.storage.add(this.max);
    }
    /**
     * Test.
     */
    @Test
    public void whenExecute2ThreadThenFiftyAndHundred() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(1, 2, 60);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                storage.transfer(2, 1, 10);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(50, is(this.alex.getAmount()));
        assertThat(100, is(this.max.getAmount()));
    }
    /**
     * Test.
     */
    @Test
    public void whenUpdateUserAmountFiftyThenAmountSeventy() {
        this.storage.update(new User(2, 70));
        assertThat(70, is(this.storage.getUser(2).getAmount()));
    }
    /**
     * Test.
     */
    @Test
    public void whenDeleteUserThenNull() {
        this.storage.delete(this.max);
        assertNull(this.storage.getUser(2));
    }
}