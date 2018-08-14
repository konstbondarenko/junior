package ru.kbond.multithreading.deadlock;

import java.util.concurrent.CountDownLatch;

/**
 * Guaranteed deadlock.
 *
 * @author kbondarenko
 * @version 1
 * @since 14.08.2018
 */
public class DeadLock {
    private final Friend alphonse = new Friend("Alphonse");
    private final Friend gaston = new Friend("Gaston");
    private final CountDownLatch countDownLatch = new CountDownLatch(2);

    /**
     * The method starts threads.
     */
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1");
                alphonse.bow(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2");
                gaston.bow(alphonse);
            }
        }).start();
    }

    /**
     * Class for demonstration of blocking.
     */
    private class Friend {
        private final String name;

        /**
         * Constructor.
         */
        public Friend(String name) {
            this.name = name;
        }

        /**
         * Getter.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Method for capturing the monitor.
         */
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s" + "  has bowed to me!%n", this.name, bower.getName());
            countDownLatch.countDown();
            bower.bowBack(this);
        }

        /**
         * Method for capturing the monitor.
         */
        public synchronized void bowBack(Friend bower) {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }
}
