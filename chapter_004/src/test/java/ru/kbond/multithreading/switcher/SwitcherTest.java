package ru.kbond.multithreading.switcher;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.Semaphore;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author kbondarenko
 * @version 1
 * @since 14.08.2018
 */
public class SwitcherTest {
    private Switcher switcher;

    @Before
    public void setUp() {
        this.switcher = new Switcher();
    }

    /**
     * Test.
     */
    @Test
    public void whenAddOneAppendTwoThenString() {
        this.switcher.addSymbol(1);
        this.switcher.addSymbol(2);
        assertThat("12", is(switcher.getStr()));
    }

    /**
     * Test.
     * Adding integer from 2 streams alternately,
     * get the result of a string of characters added
     * alternately to 10 pieces.
     */
    @Test
    public void whenAddIntegerFromTwoStreamsAlternatelyThenString() throws InterruptedException {
        Semaphore sem1 = new Semaphore(10);
        Semaphore sem2 = new Semaphore(0);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 20; j++) {
                    try {
                        sem1.acquire();
                        switcher.addSymbol(1);
                        if (sem1.availablePermits() == 0) {
                            sem2.release(10);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int j = 0; j < 20; j++) {
                        sem2.acquire();
                        switcher.addSymbol(2);
                        if (sem2.availablePermits() == 0) {
                            sem1.release(10);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        String result = "1111111111222222222211111111112222222222";
        assertThat(result, is(this.switcher.getStr()));
    }
}