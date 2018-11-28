package ru.kbond.trackerjdbc.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kbond.trackerjdbc.models.Comment;
import ru.kbond.trackerjdbc.models.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tracker test.
 *
 * @author kbondarenko
 * @version 1
 * @since 18.09.2018
 */
public class TrackerTest {
    private Tracker tracker;
    private Item itemOne;
    private Item itemTwo;
    private Comment comment;
    private Properties prs;
    private static final Logger LOG = LoggerFactory.getLogger(TrackerTest.class);
    private final static String CONFIG = "ru.kbond.trackerjdbc.start/db_test.properties";

    @Before
    public void setUp() {
        this.tracker = new Tracker(CONFIG);
        this.itemOne = new Item("first", "testNameFirst", 1L);
        this.itemTwo = new Item("second", "testNameSecond", 2L);
        this.comment = new Comment("desc", 3L);
        this.prs = new Properties();
        try (InputStream io = getClass().getClassLoader().getResourceAsStream(CONFIG)) {
            this.prs.load(io);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * The method opens a new connection to clear the tables.
     */
    @After
    public void tearDown() {
        this.tracker.close();
        try (Connection connection = DriverManager.getConnection(
                prs.getProperty("user_url"),
                prs.getProperty("user"),
                prs.getProperty("password")
        ); Statement st = connection.createStatement()) {
            st.addBatch("DELETE FROM comments;");
            st.addBatch("DELETE FROM items;");
            st.addBatch("ALTER SEQUENCE items_item_id_seq RESTART;");
            st.addBatch("ALTER SEQUENCE comments_comment_id_seq RESTART;");
            st.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Test that checks the method of searching for all elements of the database.
     */
    @Test
    public void whenFindAllItemsThenReturnAllItems() {
        this.tracker.add(this.itemOne);
        this.tracker.add(this.itemTwo);
        assertThat(this.tracker.findAll().get(0).getName(), is(this.itemOne.getName()));
        assertThat(this.tracker.findAll().get(1).getName(), is(this.itemTwo.getName()));
    }

    /**
     * Test that checks the search for the element name in the database.
     */
    @Test
    public void whenFindByNameItemThenTrackerHasAdjustedItem() {
        this.tracker.add(this.itemOne);
        assertThat(this.tracker.findByName("first").get(0).getName(), is(this.itemOne.getName()));
    }

    /**
     * Test that checks for an update on the id of an element in an database.
     */
    @Test
    public void whenUpdateItemThenTrackerHasRenewedItem() {
        this.tracker.add(this.itemOne);
        this.tracker.update(1, this.itemTwo);
        assertThat(this.tracker.findAll().get(0).getName(), is(this.itemTwo.getName()));
    }

    /**
     * Test that checks the deletion of an element in an database by an id.
     */
    @Test
    public void whenDeleteItemThenTrackerHasDeleteItem() {
        this.tracker.add(this.itemOne);
        this.tracker.add(this.itemTwo);
        this.tracker.delete(1);
        assertThat(this.tracker.findAll().get(0).getName(), is(this.itemTwo.getName()));
    }

    /**
     * Test that checks the search for the id of an element in an database.
     */
    @Test
    public void whenFindByIdItemThenTrackerHasAdjustedItem() {
        this.tracker.add(this.itemOne);
        this.tracker.add(this.itemTwo);
        assertThat(this.tracker.findById(2).get(0).getName(), is(this.itemTwo.getName()));
    }

    /**
     * Test adds a comment to the item and gets it.
     */
    @Test
    public void whenAddCommentForItemThenGetComment() {
        this.tracker.add(this.itemOne);
        this.tracker.addComment("desc", 1);
        assertThat(this.tracker.getComments(1).get(0).getText(), is(this.comment.getText()));
    }
}