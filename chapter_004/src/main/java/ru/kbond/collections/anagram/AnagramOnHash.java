package ru.kbond.collections.anagram;

import java.util.*;

/**
 * The class tests whether words are anagrams.
 *
 * @author kbondarenko
 * @version 1
 * @since 25.05.2018
 */
public class AnagramOnHash {
    /**
     * Field with a dictionary.
     */
    private Map<Character, Integer> dictionary = new HashMap<Character, Integer>() {
        {
            put('a', 1);
            put('б', 2);
            put('в', 3);
            put('г', 4);
            put('д', 5);
            put('е', 6);
            put('ё', 7);
            put('ж', 8);
            put('з', 9);
            put('и', 10);
            put('й', 11);
            put('к', 12);
            put('л', 13);
            put('м', 14);
            put('н', 15);
            put('о', 16);
            put('п', 17);
            put('р', 18);
            put('с', 19);
            put('т', 20);
            put('у', 21);
            put('ф', 22);
            put('х', 23);
            put('ц', 24);
            put('ч', 25);
            put('ш', 26);
            put('щ', 27);
            put('ъ', 28);
            put('ы', 29);
            put('ь', 30);
            put('э', 31);
            put('ю', 32);
            put('я', 33);
        }
    };
    /**
     * The field contains sorted words.
     */
    private Map<Integer, Map<Integer, List<String>>> sortWord = new HashMap<>();
    /**
     * The method sorts words. The first key is the "sum" of the letters of
     * the word defined by the dictionary. The object is the map where the key
     * is the length of the words, and the object is a List of words.
     *
     * @param file  text file for anagram search.
     */
    public void loadFile(String file) {
        String[] tmp = file.split("\\s");
        for (String st : tmp) {
            int res = sumWord(st);
            if (this.sortWord.containsKey(res)) {
                if (this.sortWord.get(res).containsKey(st.length())) {
                    this.sortWord.get(res).get(st.length()).add(st);
                } else {
                    this.sortWord.get(res).put(st.length(), new ArrayList<>(Arrays.asList(st)));
                }
            } else {
                this.sortWord.put(res, new HashMap<>());
                this.sortWord.get(res).put(st.length(), new ArrayList<>(Arrays.asList(st)));
            }
        }
    }
    /**
     * The method returns the found anagrams.
     *
     * @return  the found anagrams.
     */
    public List<String> anagrams() {
        List<String> result = new ArrayList<>();
        for (Integer sum : this.sortWord.keySet()) {
            for (Integer len : sortWord.get(sum).keySet()) {
                for (int i = 0; i < this.sortWord.get(sum).get(len).size(); i++) {
                    if (this.sortWord.get(sum).get(len).size() > 1) {
                        result.add(this.sortWord.get(sum).get(len).get(i));
                    }
                }
            }
        }
        return result;
    }
    /**
     * The method calculates the "sum" of a word in a dictionary.
     *
     * @param word  word for calculation.
     * @return  "sum" of the word.
     */
    private int sumWord(String word) {
        char[] tmp = word.toLowerCase().toCharArray();
        int sum = 0;
        for (char ch : tmp) {
            if (this.dictionary.containsKey(ch)) {
                sum = sum + this.dictionary.get(ch);
            }
        }
        return sum;
    }
}
