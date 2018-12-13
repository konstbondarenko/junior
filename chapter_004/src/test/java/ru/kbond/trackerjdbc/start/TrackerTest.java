package ru.kbond.trackerjdbc.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.kbond.trackerjdbc.models.Comment;
import ru.kbond.trackerjdbc.models.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
    private DataSource dataSource;
    private Item itemOne;
    private Item itemTwo;
    private Comment comment;
    private Properties prs;
    private static final Logger LOG = LogManager.getLogger(TrackerTest.class);
    private static final String CONFIG = "ru.kbond.trackerjdbc.start/db_test.properties";

    @Before
    public void setUp() {
        this.itemOne = new Item("first", "testNameFirst", 1L);
        this.itemTwo = new Item("second", "testNameSecond", 2L);
        this.comment = new Comment("desc", 3L);
        this.prs = new Properties();
        try (InputStream io = getClass().getClassLoader().getResourceAsStream(CONFIG)) {
            this.prs.load(io);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        this.dataSource = new DataSource();
        this.dataSource.setDriverClassName(this.prs.getProperty("driver"));
        this.dataSource.setUrl(this.prs.getProperty("url"));
        this.dataSource.setUsername(this.prs.getProperty("user"));
        this.dataSource.setPassword(this.prs.getProperty("password"));
        this.tracker = new Tracker(dataSource);
    }

    /**
     * Метод очищает тестовую таблицу.
     */
    @After
    public void tearDown() {
        try (Connection connection = this.dataSource.getConnection();
             Statement st = connection.createStatement()) {
            st.addBatch("DELETE FROM comments;");
            st.addBatch("DELETE FROM items;");
            st.addBatch("ALTER SEQUENCE items_item_id_seq RESTART;");
            st.addBatch("ALTER SEQUENCE comments_comment_id_seq RESTART;");
            st.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        this.dataSource.close(true);
    }

    /**
     * Тест добавляет объект в базу данных и проверяет его наличие.
     */
    @Test
    public void whenFindAllItemsThenReturnAllItems() {
        this.tracker.add(this.itemOne);
        assertThat(this.tracker.findAll().get(0).getName(), is(this.itemOne.getName()));
    }

    /**
     * Тест производит поиск объекта по имени.
     */
    @Test
    public void whenFindByNameItemThenTrackerHasAdjustedItem() {
        this.tracker.add(this.itemOne);
        assertThat(this.tracker.findByName("first").get(0).getName(), is(this.itemOne.getName()));
    }

    /**
     * Тест добавляет объект, затем обновляет его..
     */
    @Test
    public void whenUpdateItemThenTrackerHasRenewedItem() {
        this.tracker.add(this.itemOne);
        this.tracker.update(1, this.itemTwo);
        assertThat(this.tracker.findAll().get(0).getName(), is(this.itemTwo.getName()));
    }

    /**
     * Тест производит добавление, а после, удаление объекта из базы данных.
     */
    @Test
    public void whenDeleteItemThenTrackerHasDeleteItem() {
        this.tracker.add(this.itemOne);
        this.tracker.add(this.itemTwo);
        this.tracker.delete(1);
        assertThat(this.tracker.findAll().get(0).getName(), is(this.itemTwo.getName()));
    }

    /**
     * Тест производит поиск объекта по его id.
     */
    @Test
    public void whenFindByIdItemThenTrackerHasAdjustedItem() {
        this.tracker.add(this.itemOne);
        this.tracker.add(this.itemTwo);
        assertThat(this.tracker.findById(2).get(0).getName(), is(this.itemTwo.getName()));
    }

    /**
     * Тест добавляет комментарий к объекту, затем проверяет его наличие.
     */
    @Test
    public void whenAddCommentForItemThenGetComment() {
        this.tracker.add(this.itemOne);
        this.tracker.addComment("desc", 1);
        assertThat(this.tracker.getComments(1).get(0).getText(), is(this.comment.getText()));
    }
}