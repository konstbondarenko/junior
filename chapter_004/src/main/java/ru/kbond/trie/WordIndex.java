package ru.kbond.trie;

import java.util.List;

/**
 * The class allows you to find all of
 * its positions in a file by a given word.
 *
 * @author kbondarenko
 * @since 22.05.2018
 * @version 1
 */
public class WordIndex {
    /**
     * Prefix tree field.
     */
    private Trie trie = new Trie();
    /**
     * An empty string for determining the end of a word.
     */
    private final static StringBuilder EMPTY = new StringBuilder();
    /**
     * The class checks the text for special
     * characters and separates words.
     *
     * @param chars  checked character.
     * @return  {@code true} if the character
     *          matches the pattern.
     */
    private boolean checkPattern(char chars) {
        boolean success = true;
        char[] pattern = new char[] {
                ' ', ',', '.', ';', ':', '!',
                '?', '[', ']', '(', ')', '-', '\n'};
        for (int i = 0; i != pattern.length; i++) {
            if (chars == pattern[i]) {
                success = false;
                break;
            }
        }
        return success;
    }
    /**
     * The method loads a text file, checks it against a
     * given pattern, and loads it into the prefix tree.
     * Returns uppercase letters to lowercase letters.
     *
     * @param fileName  source file.
     */
    public void loadFile(String fileName) {
        char[] source = fileName.toCharArray();
        int count = 0;
        StringBuilder result = new StringBuilder();
        while (count < source.length) {
            if (checkPattern(source[count])) {
                result.append(Character.toLowerCase(source[count]));
            } else if (!result.toString().equals(EMPTY.toString())) {
                this.trie.add(result.toString(), count);
                result = new StringBuilder();
            }
            if (count == source.length - 1
                    && !result.toString().equals(EMPTY.toString())) {
                this.trie.add(result.toString(), count + 1);
            }
            count++;
        }
    }
    /**
     * The method returns in numeric form all
     * positions of the search word in the file.
     * Returns uppercase letters to lowercase letters.
     *
     * @param searchWord  search word.
     * @return  list of word positions in a file.
     */
    public List<Integer> getIndexes4Word(String searchWord) {
        return this.trie.find(searchWord);
    }
}
