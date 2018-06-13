package ru.kbond.multithreading.lock;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Simple lock test.
 *
 * @author kbondarenko
 * @since 08.06.2018
 * @version 1
 */
public class SimpleLockTest {
    private class Count {
        private int count;
        public void increment() {
            this.count++;
        }
        public int getCount() {
            return count;
        }
    }
    /**
     * Test. With multiple calls, the result is always correct.
     */
    @Test
    public void whenThreadIsLockedThenRemainingThreadsAreWaitingForUnlock() throws InterruptedException {
        SimpleLock simpleLock = new SimpleLock();
        Count count = new Count();
        List<Thread> threads = new ArrayList<>();
        Thread thread;
        for (int i = 0; i < 10; i++) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    simpleLock.lock();
                    try {
                        Thread.sleep(100);
                        for (int j = 0; j < 500; j++) {
                            count.increment();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        simpleLock.unlock();
                    }

                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread th : threads) {
            th.join();
        }
        assertThat(count.getCount(), is(5000));
    }
}