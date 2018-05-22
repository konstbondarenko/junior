package ru.kbond.trie;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

/**
 * Test.
 * @author kbondarenko
 * @since 20.03.2018
 * @version 1
 */
public class TrieTest {
    private WordIndex wordIndex;
    @Before
    public void setUp() {
        this.wordIndex = new WordIndex();
        String file = "Сирени, умытые ливнем,\n"
                +
                "Над серым асфальтом парят,\n"
                +
                "И серое небо над ними —\n"
                +
                "Их свадебный вдовий наряд.";
        this.wordIndex.loadFile(file);
    }
    /**
     * Test.
     */
    @Test
    public void whenTestInsertAndFindWordPositionThenPositionSixtyEight() {
        List<Integer> expected = new ArrayList<>(Collections.singletonList(68));
        assertThat(this.wordIndex.getIndexes4Word("ними"), is(expected));
    }
    /**
     * Test.
     */
    @Test
    public void whenTestInsertAndFindWordPositionThenPositionSeventyFive() {
        List<Integer> expected = new ArrayList<>(Collections.singletonList(75));
        assertThat(this.wordIndex.getIndexes4Word("Их"), is(expected));
    }
    /**
     * Test.
     */
    @Test
    public void whenTestInsertAndFindTwoWordPosition() {
        List<Integer> expected = new ArrayList<>(Arrays.asList(24, 64));
        assertThat(this.wordIndex.getIndexes4Word("над"), is(expected));
    }
    /**
     * Test.
     */
    @Test
    public void whenSearchForEmptyStringThenNull() {
        assertNull(this.wordIndex.getIndexes4Word(""));
    }
}
