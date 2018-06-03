package ru.kbond.multithreading.synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Count.
 *
 * @author kbondarenko
 * @since 02.06.2018
 * @version 1
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;
    /**
     * The method increases the counter by one.
     */
    public synchronized void increment() {
        this.value++;
    }
    /**
     * Getter.
     */
    public synchronized int get() {
        return this.value;
    }
}
