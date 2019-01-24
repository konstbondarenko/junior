package ru.kbond.observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.kbond.observer.listener.ConsoleWriter;
import ru.kbond.observer.listener.FileWriter;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Тест.
 *
 * @author kbondarenko
 * @version 1
 * @since 23.01.2019
 */
public class EventManagerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(this.outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(this.originalOut);
    }

    /**
     * Тест производит проверку слушателя "ConsoleWriter".
     */
    @Ignore
    @Test
    public void consoleLog() {
        Logger logger = new Logger();
        logger.events.subscribe(new ConsoleWriter());
        logger.info("inf");
        logger.error("err");
        String expected = "info: inf" + "\r\n" + "error: err" + "\r\n";
        assertThat(expected, is(this.outContent.toString()));
    }

    /**
     * Тест производит проверку слушателя "FileWriter".
     */
    @Ignore
    @Test
    public void fileWriter() throws IOException {
        File destOut = File.createTempFile("log", ".txt");
        Logger logger = new Logger();
        logger.events.subscribe(new FileWriter(String.valueOf(destOut)));
        logger.info("inf");
        logger.error("err");
        BufferedReader reader = new BufferedReader(new FileReader(destOut));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
            result.append("\r\n");
        }
        String expected = "inf" + "\r\n" + "err" + "\r\n";
        assertThat(expected, is(result.toString()));
    }
}