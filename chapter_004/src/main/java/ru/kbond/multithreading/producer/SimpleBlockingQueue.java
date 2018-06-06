package ru.kbond.multithreading.producer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Blocking queue.
 *
 * @author kbondarenko
 * @since 05.06.2018
 * @version 1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Default initial capacity.
     */
    private final static int DEFAULT_CAPACITY = 10;
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    /**
     * User-defined capacity.
     */
    private final int capacity;
    /**
     * Constructor with an initial default capacity.
     */
    public SimpleBlockingQueue() {
        this.capacity = DEFAULT_CAPACITY;
    }
    /**
     * Constructor with an initial user capacity.
     *
     * @param capacity  user-defined capacity.
     */
    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }
    /**
     * Getter.
     *
     * @return  capacity.
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * The method adds an object to the queue.
     * If the queue is full, it waits.
     *
     * @param value  accepted value.
     * @throws InterruptedException
     */
    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            if (this.queue.size() <= this.getCapacity()) {
                this.queue.offer(value);
                this.notify();
            } else {
                this.wait();
            }
        }
    }
    /**
     * The method returns an object from the queue if
     * the queue is empty. If there are no objects in
     * the collection, then the current thread is placed
     * in the waiting state.
     *
     * @return  queue object.
     * @throws InterruptedException
     */
    public T poll() throws InterruptedException {
        synchronized (this) {
            while (this.queue.isEmpty()) {
                this.wait();
            }
            return this.queue.poll();
        }
    }
}
