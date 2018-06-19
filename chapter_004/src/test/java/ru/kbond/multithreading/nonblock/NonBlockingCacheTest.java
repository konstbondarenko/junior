package ru.kbond.multithreading.nonblock;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

/**
 * Test.
 *
 * @author kbondarenko
 * @version 1
 * @since 19.06.2018
 */
public class NonBlockingCacheTest {
    private NonBlockingCache nonBlockingCache;
    private Base base1;
    private Base base2;

    @Before
    public void setUp() {
        this.nonBlockingCache = new NonBlockingCache();
        this.base1 = new Base(1, "one");
        this.base2 = new Base(1, "second");
    }

    /**
     * Test.
     */
    @Test
    public void whenAddModelThanGetModel() {
        this.nonBlockingCache.add(this.base1);
        assertThat(this.base1, is(this.nonBlockingCache.get(1)));
    }

    /**
     * Test.
     */
    @Test
    public void whenDeleteModelThanGetNullsByKey() {
        this.nonBlockingCache.add(this.base1);
        this.nonBlockingCache.delete(1);
        assertNull(this.nonBlockingCache.get(1));
    }

    /**
     * Test.
     */
    @Test
    public void whenUpdateModelThanGetNewModel() {
        this.nonBlockingCache.add(this.base1);
        this.nonBlockingCache.update(1, this.base2);
        assertThat(this.base2, is(this.nonBlockingCache.get(1)));
    }
}