package ru.kbond.collections.trie;

import java.util.*;

/**
 * Prefix tree node.
 *
 * @author kbondarenko
 * @since 22.05.2018
 * @version 1
 */
public class TrieNode {
    /**
     * The field stores a list of children by key.
     */
    private HashMap<Character, TrieNode> children;
    /**
     * The field stores the entry position of the word in the file.
     */
    private List<Integer> wordPosition = new ArrayList<>();
    /**
     * The text of the node.
     */
    private String text;
    /**
     * Is the word complete.
     */
    private boolean isWord;
    /**
     * Constructor.
     */
    public TrieNode() {
        children = new HashMap<>();
        text = "";
        isWord = false;
    }
    /**
     * Constructor.
     *
     * @param text  the text of the child
     *              node when creating.
     */
    public TrieNode(String text) {
        this();
        this.text = text;
    }
    /**
     * Getter.
     * @return  list of child nodes.
     */
    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }
    /**
     * Getter.
     *
     * @return  text node.
     */
    public String getText() {
        return text;
    }
    /**
     * Getter.
     */
    public boolean isWord() {
        return isWord;
    }
    /**
     * Setter.
     */
    public void setIsWord(boolean word) {
        isWord = word;
    }
    /**
     * Setter.
     */
    public void setWordPosition(int position) {
        this.wordPosition.add(position);
    }
    /**
     * Getter.
     */
    public List<Integer> getWordPosition() {
        return wordPosition;
    }
    /**
     * ToString.
     */
    @Override
    public String toString() {
        return text;
    }
}