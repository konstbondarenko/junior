package ru.kbond.collections.trie;

import java.util.*;

/**
 * Prefix tree.
 *
 * @author kbondarenko
 * @since 22.05.2018
 * @version 1
 */
public class Trie {
    /**
     * Prefix tree node.
     */
    private TrieNode root;
    private int size;
    /**
     * Constructor.
     */
    public Trie() {
        root = new TrieNode();
        size = 0;
    }
    /**
     * Getter.
     */
    public int getSize() {
        return size;
    }
    /**
     * The method places the word and
     * its position in the prefix tree.
     *
     * @param word  incoming word.
     * @param position  word position.
     * @return  {@code true}  if the word is added.
     */
    public boolean add(String word, int position) {
        boolean checkAdd = false;
        TrieNode trie = this.root;
        int counter = 0;
        char[] chars = word.toCharArray();
        while (counter < chars.length) {
            Set<Character> childs = trie.getChildren().keySet();
            if (!childs.contains(chars[counter])) {
                insertChar(trie, chars[counter]);
                if (counter == chars.length - 1) {
                    getChild(trie, chars[counter]).setIsWord(true);
                    getChild(trie, chars[counter]).setWordPosition(position - word.length());
                    this.size++;
                    checkAdd = true;
                    break;
                }
            }
            trie = getChild(trie, chars[counter]);
            if (trie.getText().equals(word)) {
                if (!trie.isWord()) {
                    trie.setIsWord(true);
                }
                trie.setWordPosition(position - word.length());
                this.size++;
                checkAdd = true;
                break;
            }
            counter++;
        }
        return checkAdd;
    }
    /**
     * Search for the position of a word in a file.
     * Returns uppercase letters to lowercase letters.
     *
     * @param str  search word.
     * @return  list of word positions in a file.
     */
    public List<Integer> find(String str) {
        boolean checkFind = false;
        Map<Character, TrieNode> children = this.root.getChildren();
        TrieNode t = new TrieNode();
        for (int i = 0; i < str.length(); i++) {
            char c = str.toLowerCase().charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.getChildren();
                checkFind = true;
            } else {
                checkFind = false;
                break;
            }
        }
        return checkFind ? t.getWordPosition() : null;
    }
    /**
     * Search for a node by key.
     *
     * @param trie  root node.
     * @param c  key.
     * @return  child node.
     */
    private TrieNode getChild(TrieNode trie, Character c) {
        return trie.getChildren().get(c);
    }
    /**
     * Creates a child node.
     *
     * @param trie  root node.
     * @param c  new key.
     * @return  new child node.
     */
    private TrieNode insertChar(TrieNode trie, Character c) {
        TrieNode next = new TrieNode(trie.getText() + c.toString());
        trie.getChildren().put(c, next);
        return next;
    }
}
