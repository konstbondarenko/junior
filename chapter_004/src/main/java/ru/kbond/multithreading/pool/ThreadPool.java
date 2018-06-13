package ru.kbond.multithreading.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Thread pool.
 *
 * @author kbondarenko
 * @since 08.06.2018
 * @version 1
 */
public class ThreadPool {
    /**
     * Number of cores in the system.
     */
    private final int numOfCores;
    private final List<Thread> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
    /**
     * Constructor.
     *
     * Starts threads by the number of cores in the system.
     */
    public ThreadPool() {
        this.numOfCores = Runtime.getRuntime().availableProcessors();
    }
    /**
     * Start threads.
     */
    public void threadInit() {
        for (int i = 0; i < this.numOfCores; i++) {
            this.threads.add(new ThreadWorker());
            this.threads.get(i).start();
        }
    }
    /**
     * The method adds tasks to the blocking queue.
     *
     * @param job  tasks.
     */
    public void work(Runnable job) {
        this.tasks.add(job);
    }
    /**
     * Indicates whether the pool should complete its work.
     */
    public void shutDown() throws InterruptedException {
        for (Thread thread : this.threads) {
            thread.interrupt();
        }
        for (Thread thread : this.threads) {
            thread.join();
        }
    }
    /**
     * The method takes a task from the queue
     * and passes it to the free thread for execution.
     */
    private class ThreadWorker extends Thread {
        @Override
        public void run() {
            Runnable job;
            while (true) {
                job = tasks.poll();
                if (job != null) {
                    job.run();
                }
                if (tasks.isEmpty() && isInterrupted()) {
                    break;
                }
            }
        }
    }
}
