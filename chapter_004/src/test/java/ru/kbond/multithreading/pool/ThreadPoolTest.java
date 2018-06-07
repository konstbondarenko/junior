package ru.kbond.multithreading.pool;

import org.junit.Test;
import ru.kbond.multithreading.synchronization.Count;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Thread pool test.
 *
 * @author kbondarenko
 * @since 08.06.2018
 * @version 1
 */
public class ThreadPoolTest {
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
    public void whenExecuteNumberOfCoresThreadThen10() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        Count count = new Count();
        for (int i = 0; i < 10; i++) {
            threadPool.work(new ThreadCount(count));
        }
        threadPool.shutDown();
        assertThat(count.get(), is(10));
    }
}