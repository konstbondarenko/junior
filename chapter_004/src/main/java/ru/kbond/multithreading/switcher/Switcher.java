package ru.kbond.multithreading.switcher;

/**
 * The class adds characters by
 * converting a number to a string.
 *
 * @author kbondarenko
 * @version 1
 * @since 14.08.2018
 */
public class Switcher {
    final private StringBuffer str;

    /**
     * Constructor.
     */
    public Switcher() {
        this.str = new StringBuffer();
    }

    /**
     * Method adds characters by
     * converting a number to a string.
     *
     * @param symbol accepted number.
     */
    public void addSymbol(int symbol) {
        this.str.append(Integer.toString(symbol));
    }

    /**
     * Getter.
     */
    public String getStr() {
        return str.toString();
    }
}
