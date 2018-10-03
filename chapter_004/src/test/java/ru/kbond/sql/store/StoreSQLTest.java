package ru.kbond.sql.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.kbond.sql.store.pojo.Entry;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * StoreSQL test.
 *
 * @author kbondarenko
 * @version 1
 * @since 18.09.2018
 */
public class StoreSQLTest {
    private StoreSQL storeSQL;

    @Before
    public void setUp() {
        this.storeSQL = new StoreSQL();
    }

    @After
    public void tearDown() {
        this.storeSQL.clearTable();
        this.storeSQL.close();
    }

    /**
     * Test.
     */
    @Test
    public void whenAddTwoLinesToDBThenGetListSizeTwoValues() {
        this.storeSQL.generate(2);
        List<Entry> entry = this.storeSQL.getEntries();
        assertThat(entry.size(), is(2));
    }
}