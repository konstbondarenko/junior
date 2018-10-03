package ru.kbond.sql.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kbond.sql.store.pojo.Entry;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * SQLite data generation.
 * The class generates a specified number of fields in the database.
 * SQLite database used.
 *
 * @author kbondarenko
 * @version 1
 * @since 03.10.2018
 */
public class StoreSQL implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
    private final static String CONFIG = "ru.kbond.sql.store/db_store.properties";
    private Properties prs;
    private Connection connection;

    /**
     * Constructor.
     * A default connection is created with settings from the CONFIG.
     */
    public StoreSQL() {
        this.prs = new Properties();
        loadProperties(CONFIG);
        if (this.connection == null) {
            connectDB();
        }
    }

    /**
     * Constructor.
     *
     * @param config configuration file path.
     */
    public StoreSQL(final String config) {
        this.prs = new Properties();
        loadProperties(config);
        if (this.connection == null) {
            connectDB();
        }
    }

    /**
     * The method loads the settings file at the specified path.
     *
     * @param properties configuration file path.
     */
    private void loadProperties(final String properties) {
        try (InputStream io = getClass().getClassLoader().getResourceAsStream(properties)) {
            this.prs.load(io);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * The method makes a connection to the database.
     */
    private void connectDB() {
        try {
            this.connection = DriverManager.getConnection(this.prs.getProperty("user_url"));
            createTableDB();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * The method creates a table in the database.
     */
    private void createTableDB() {
        try (Statement st = this.connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS entry(field INTEGER)");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * The method validates the incoming number and passes it
     * on to create fields in the database.
     *
     * @param n number of lines to create.
     * @throws IllegalArgumentException thrown when entering a negative number.
     */
    public void generate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    String.format("The number must not be negative n = %s", n));
        }
        try {
            generator(n);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * The method generates the specified number of rows.
     *
     * @param n number of lines to create.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     */
    private void generator(int n) throws SQLException {
        try (PreparedStatement st = this.connection.prepareStatement(
                "INSERT INTO entry(field) VALUES(?)")) {
            this.connection.setAutoCommit(false);
            for (int i = 1; i <= n; i++) {
                st.setInt(1, i);
                st.addBatch();
            }
            st.executeBatch();
            this.connection.commit();
        } catch (SQLException e) {
            this.connection.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            this.connection.setAutoCommit(true);
        }
    }

    /**
     * The method returns a list of objects equal to the number of records in the table.
     *
     * @return ArrayList with entry objects.
     */
    public List<Entry> getEntries() {
        int sum = sumOfCount();
        List<Entry> entries = new ArrayList<>(sum + 10);
        try (Statement st = this.connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT field FROM entry")
        ) {
            while (resultSet.next()) {
                entries.add(new Entry(resultSet.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * The method counts the number of records in the database for
     * the subsequent list generation.
     *
     * @return number of records in the database.
     */
    private int sumOfCount() {
        int sum = 0;
        try (Statement st = this.connection.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT COUNT(field) AS count FROM entry")
        ) {
            resultSet.next();
            sum = resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    /**
     * The method clears the table of records.
     */
    public void clearTable() {
        try (Statement st = this.connection.createStatement()) {
            st.executeUpdate("DELETE FROM entry;");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * The method closes the connection.
     */
    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
