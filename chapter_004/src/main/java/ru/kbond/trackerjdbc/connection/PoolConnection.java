package ru.kbond.trackerjdbc.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class PoolConnection - создает пул коннектов.
 *
 * @author kbondarenko
 * @version 1
 * @since 10.12.2018
 */
public class PoolConnection extends DataSource {
    private static final Logger LOG = LogManager.getLogger(PoolConnection.class.getName());
    private static final String CONFIG = "ru.kbond.trackerjdbc.start/db.properties";
    private Properties prs;

    /**
     * Конструктор.
     */
    public PoolConnection() {
        loadProperties(CONFIG);
    }

    /**
     * Конструктор.
     * @param config файл с конфигурацией подключения.
     */
    public PoolConnection(String config) {
        loadProperties(config);
    }

    /**
     * Метод загружает настройки.
     * @param properties параметры подключениия.
     */
    private void loadProperties(String properties) {
        this.prs = new Properties();
        try (InputStream io = getClass().getClassLoader().getResourceAsStream(properties)) {
            this.prs.load(io);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        connectDB();
    }

    /**
     * Метод создает подключение к базе данных.
     */
    private void connectDB() {
        this.setDriverClassName(this.prs.getProperty("driver"));
        this.setUrl(this.prs.getProperty("url"));
        this.setUsername(this.prs.getProperty("user"));
        this.setPassword(this.prs.getProperty("password"));
        this.setMinIdle(5);
        this.setMaxIdle(10);
    }
}
