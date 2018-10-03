package ru.kbond.sql.store;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * SumAttributesSAX test.
 *
 * @author kbondarenko
 * @version 1
 * @since 18.09.2018
 */
public class SumAttributesSAXTest {
    private final static String DEST_TEST = "ru.kbond.sql.store/dest.xml";
    private File dest;

    @Before
    public void setUp() {
        this.dest = new File(getClass().getClassLoader().getResource(DEST_TEST).getFile());
    }

    /**
     * Test.
     */
    @Test
    public void whenPassXmlFileForParsingThanGetSumOfAttributes() {
        SumAttributesSAX atributsSAX = new SumAttributesSAX(dest);
        atributsSAX.init();
        assertThat(atributsSAX.getSumAttrib(), is(3));
    }
}