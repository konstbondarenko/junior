package ru.kbond.multithreading.lock;

/**
 * Simple lock.
 * It blocks resources between lock and unlock.
 * Example of use:
 *
 *  <pre> {@code
 * SimpleLock l = ...;
 * l.lock();
 * try {
 *   // access the resource protected by this lock
 * } finally {
 *   l.unlock();
 * }}</pre>
 *
 * @author kbondarenko
 * @since 08.06.2018
 * @version 1
 */
public class SimpleLock {
    /**
     * Flag to check the status of the lock.
     */
    private boolean locked = false;
    /**
     * Thread owner lock.
     */
    private Thread owner;
    /***
     * Sets the lock.
     */
    public final void lock() {
        synchronized (this) {
            while (this.locked) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.owner = Thread.currentThread();
        this.locked = true;
    }
    /**
     * Releases the lock.
     */
    public final void unlock() {
        synchronized (this) {
            while (Thread.currentThread().equals(this.owner)) {
                this.owner = null;
                this.locked = false;
                this.notifyAll();
            }
        }
    }
}
