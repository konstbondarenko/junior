package ru.kbond.sql.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.kbond.sql.store.pojo.Entry;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * StoreXML test.
 *
 * @author kbondarenko
 * @version 1
 * @since 18.09.2018
 */
public class StoreXMLTest {
    private File target;
    private StoreSQL storeSQL;
    private StoreXML storeXML;

    @Before
    public void setUp() throws IOException {
        this.target = File.createTempFile("temp", ".xml");
        this.storeSQL = new StoreSQL();
        this.storeXML = new StoreXML(this.target);
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
    public void whenPassListEntryThanGetXmlFile() throws JAXBException, IOException {
        this.storeSQL.generate(2);
        List<Entry> entry = this.storeSQL.getEntries();
        this.storeXML.save(entry);
        BufferedReader reader = new BufferedReader(new FileReader(this.target));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><entries>"
                +
                "    <entry field=\"1\"/>    <entry field=\"2\"/></entries>";
        assertThat(expected, is(result.toString()));
    }
}