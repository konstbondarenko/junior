package ru.kbond.multithreading.synchronization;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Count test.
 *
 * @author kbondarenko
 * @since 02.06.2018
 * @version 1
 */
public class CountTest {
    /**
     * Class describes a thread with a counter.
     */
    private class ThreadCount extends Thread {
        private final Count count;
        /**
         * Constructor.
         */
        private ThreadCount(final Count count) {
            this.count = count;
        }
        /**
         * The method increases the counter.
         */
        @Override
        public void run() {
            this.count.increment();
        }
    }
    /**
     * Test.
     */
    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }
}