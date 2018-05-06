package ru.kbond.anagram;

import java.util.Arrays;

/**
 * The class tests whether words are anagrams.
 *
 * @author kbondarenko
 * @since 06.05.2018
 * @version 1
 */
public class Anagram {
    /**
     * Method tests whether words are anagrams.
     *
     * @param basic  first word.
     * @param verifiable  second word.
     * @return  {@code true} if there is an anagram.
     */
    public boolean checkAnagram(String basic, String verifiable) {
        boolean check = false;
        if (charSort(basic).equals(charSort(verifiable))) {
            check = true;
        }
        return check;
    }
    /**
     * The method breaks down words into
     * letters and sorts for further comparison.
     *
     * @param word  word for sorting.
     * @return  word after sorting.
     */
    private String charSort(String word) {
        char[] ch = word.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
    }
}
