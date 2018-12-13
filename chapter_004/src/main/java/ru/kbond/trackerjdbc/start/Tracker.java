package ru.kbond.trackerjdbc.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kbond.trackerjdbc.models.Comment;
import ru.kbond.trackerjdbc.models.Item;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Tracker - производит работу с базой данных.
 *
 * @author kbondarenko
 * @version 1
 * @since 09.12.2018
 */
public class Tracker {
    private static final Logger LOG = LogManager.getLogger(Tracker.class.getName());
    private final DataSource dataSource;

    /**
     * Конструктор.
     *
     * @param dataSource - пул коннектов для соединения с базой данных.
     */
    public Tracker(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Метод для добавления элемента.
     *
     * @param item принимаемый объект.
     */
    public void add(Item item) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "INSERT INTO items(name, description, create_date) values(?,?,?)")
        ) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод для обновления элемента по id.
     *
     * @param item принимаемый объект.
     */
    public void update(int id, Item item) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "UPDATE items SET name=?, description=?, create_date=? WHERE item_id=?")
        ) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setTimestamp(3, new Timestamp(item.getCreated()));
            st.setInt(4, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод для удаления элемента по id.
     *
     * @param id принимаемый номер.
     */
    public void delete(int id) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "DELETE FROM items WHERE item_id=?")
        ) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод возвращает все Item находящиеся в базе данных.
     *
     * @return результат.
     */
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM items");
             ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                Item item = new Item(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("create_date").getTime()
                );
                item.setId(rs.getInt("item_id"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод возвращает Item по имени.
     *
     * @param key принимаемое имя.
     * @return результат.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM items AS t Where t.name=?")

        ) {
            st.setString(1, key);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getTimestamp("create_date").getTime()
                    );
                    item.setId(rs.getInt("item_id"));
                    result.add(item);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод возвращает item по его id.
     *
     * @param id принимаемый номер.
     * @return результат.
     */
    public List<Item> findById(int id) {
        List<Item> result = new ArrayList<>(1);
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM items AS t Where t.item_id=?")
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getTimestamp("create_date").getTime()
                    );
                    item.setId(rs.getInt("item_id"));
                    result.add(item);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод добавляет комментарий к уже существующему Item.
     *
     * @param comment - текст комментария.
     * @param itemId  - id Item.
     */
    public void addComment(String comment, int itemId) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "INSERT INTO comments (comment, item_id, create_date) values(?,?,?)")
        ) {
            st.setString(1, comment);
            st.setInt(2, itemId);
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод возвращает комментарии искомого Item.
     *
     * @param id - id Item.
     * @return - комментарии.
     */
    public List<Comment> getComments(int id) {
        List<Comment> result = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT comment, comment_id, create_date FROM comments WHERE item_id=?")
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment(
                            rs.getString("comment"),
                            rs.getTimestamp("create_date").getTime()
                    );
                    comment.setId(rs.getInt("comment_id"));
                    result.add(comment);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
