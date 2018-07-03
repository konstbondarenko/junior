package ru.kbond.collections.anagram;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 09.05.2018
 * @version 1
 */
public class AnagramTest {
    /**
     * Test.
     */
    @Test
    public void shouldReturnTrueIfTheWordsTheAnagram() {
        Anagram anagram = new Anagram();
        String mama = "mama";
        String amam = "amam";
        boolean result = anagram.checkAnagram(mama, amam);
        assertThat(result, is(true));
    }
}