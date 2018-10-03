package ru.kbond.sql.store;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * ConvertXSQT test.
 *
 * @author kbondarenko
 * @version 1
 * @since 18.09.2018
 */
public class ConvertXSQTTest {
    private final static String SOURCE_TEST = "ru.kbond.sql.store/source.xml";
    private final static String SCHEME = "ru.kbond.sql.store/convertTest.xsl";
    private File source;
    private File scheme;
    private File destOut;
    private ConvertXSQT convertXSQT;

    @Before
    public void setUp() throws IOException {
        this.source = new File(getClass().getClassLoader().getResource(SOURCE_TEST).getFile());
        this.scheme = new File(getClass().getClassLoader().getResource(SCHEME).getFile());
        this.destOut = File.createTempFile("dest_output", ".xml");
        this.convertXSQT = new ConvertXSQT();
    }

    /**
     * Test.
     */
    @Test
    public void whenPassXmlFileThenGetTransformedXml() throws IOException {
        this.convertXSQT.convert(this.source, this.destOut, this.scheme);
        BufferedReader reader = new BufferedReader(new FileReader(this.destOut));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><entry href=\"1\"/><entry href=\"2\"/></entries>";
        assertThat(expected, is(result.toString()));
    }
}