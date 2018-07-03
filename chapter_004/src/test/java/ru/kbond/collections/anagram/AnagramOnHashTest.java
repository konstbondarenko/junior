package ru.kbond.collections.anagram;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test.
 * @author kbondarenko
 * @since 25.03.2018
 * @version 1
 */
public class AnagramOnHashTest {
    /**
     * Test.
     */
    @Test
    public void whenEnterTextThenAnagrams() {
        AnagramOnHash anagramOnHash = new AnagramOnHash();
        String test = "торт Тор рОт крот торк бор человек еквочел";
        anagramOnHash.loadFile(test);
        List<String> result = anagramOnHash.anagrams();
        List<String> expected = Arrays.asList("человек", "еквочел", "крот", "торк", "Тор", "рОт");
        assertThat(result, is(expected));
    }
}