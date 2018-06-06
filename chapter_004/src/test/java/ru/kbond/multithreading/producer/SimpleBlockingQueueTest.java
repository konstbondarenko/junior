package ru.kbond.multithreading.producer;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 *
 * @author kbondarenko
 * @since 05.06.2018
 * @version 1
 */
public class SimpleBlockingQueueTest {
    /**
     * Test.
     */
    @Test
    public void whenStart2ThreadThenProducerWriteDataConsumerReadData() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread threadProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 4; i++) {
                        queue.offer(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    try {
                        assertThat(i, is(queue.poll()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadProducer.start();
        threadConsumer.start();
        threadProducer.join();
        threadConsumer.join();
    }
}