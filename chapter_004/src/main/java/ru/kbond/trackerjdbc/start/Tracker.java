package ru.kbond.trackerjdbc.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kbond.trackerjdbc.models.Comment;
import ru.kbond.trackerjdbc.models.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class Tracker - производит работу с базой данных.
 *
 * @author kbondarenko
 * @version 1
 * @since 18.09.2018
 */
public class Tracker implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);
    private static final String DB_NOT_EXISTS = "f";
    private final static String CONFIG = "db.properties";
    private Properties prs;
    private Connection conn = null;

    /**
     * Constructor.
     */
    public Tracker() {
        this.prs = new Properties();
        loadProperties(CONFIG);
        if (conn == null) {
            checkDB();
        }
    }

    /**
     * Constructor.
     *
     * @param properties - конфигурация для подключения к базе данных.
     */
    public Tracker(String properties) {
        this.prs = new Properties();
        loadProperties(properties);
        if (conn == null) {
            checkDB();
        }
    }

    /**
     * Загрузка конфигурации для подключения к базе данных.
     * @param properties - конфигурация для подключения к базе данных.
     */
    private void loadProperties(String properties) {
        try (InputStream io = getClass().getClassLoader().getResourceAsStream(properties)) {
            prs.load(io);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Создание дефолтного подключения для проверки наличия базы данных из конфигурации.
     */
    private void checkDB() {
        try (Connection connection = DriverManager.getConnection(
                prs.getProperty("default_url"),
                prs.getProperty("user"),
                prs.getProperty("password")
        );
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(this.prs.getProperty("sql.check_database"))
        ) {
            rs.next();
            if (rs.getString("exists").equals(DB_NOT_EXISTS)) {
                st.executeUpdate(this.prs.getProperty("sql.create_user_db"));
                connectDB();
            } else {
                connectDB();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Создаёт таблицы для базы данных.
     */
    private void createTableDB() {
        try (Statement st = conn.createStatement()) {
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS items(item_id serial PRIMARY KEY, name VARCHAR(255), description VARCHAR(2000), create_date TIMESTAMP);"
            );
            st.addBatch(
                    "CREATE TABLE IF NOT EXISTS comments(comment_id serial PRIMARY KEY, comment TEXT, item_id INTEGER REFERENCES items(item_id), create_date TIMESTAMP);"
            );
            st.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Подключение к базе данных из файла настроек.
     */
    private void connectDB() {
        try {
            this.conn = DriverManager.getConnection(
                    prs.getProperty("user_url"),
                    prs.getProperty("user"),
                    prs.getProperty("password")
            );
            createTableDB();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод для добавления элемента.
     * @param item принимаемый объект.
     */
    public void add(Item item) {
        try (
                PreparedStatement st = conn.prepareStatement(
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
     * @param item принимаемый объект.
     */
    public void update(int id, Item item) {
        try (
                PreparedStatement st = conn.prepareStatement(
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
     * @param id принимаемый номер.
     */
    public void delete(int id) {
        try (
                PreparedStatement st = conn.prepareStatement(
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
     * @return результат.
     */
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (
                PreparedStatement st = conn.prepareStatement(
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
     * @param key принимаемое имя.
     * @return результат.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (
                PreparedStatement st = conn.prepareStatement(
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
     * @param id принимаемый номер.
     * @return результат.
     */
    public List<Item> findById(int id) {
        List<Item> result = new ArrayList<>(1);
        try (
                PreparedStatement st = conn.prepareStatement(
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
     * @param comment - текст комментария.
     * @param itemId - id Item.
     */
    public void addComment(String comment, int itemId) {
        try (PreparedStatement st = conn.prepareStatement(
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
     * @param id - id Item.
     * @return - комментарии.
     */
    public List<Comment> getComments(int id) {
        List<Comment> result = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(
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

    /**
     * Метод закрывает соединение.
     */
    @Override
    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
