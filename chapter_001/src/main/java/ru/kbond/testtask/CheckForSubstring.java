package ru.kbond.testtask;

/**
 * Class CheckForSubstring программа проверяющая, что строка sub является подстрокой origin.
 * @author kbondarenko
 * @since 28.04.2017
 * @version 1
 */
public class CheckForSubstring {
	/**
	 * Метод для проверки подстроки.
	 * @param origin строка.
	 * @param sub подстрока.
	 * @return результат
	 */
    boolean contains(String origin, String sub) {
        char[] originChar = origin.toCharArray();
        char[] subChar = sub.toCharArray();
        for (int y = 0; y < originChar.length; y++) {
            if (originChar[y] == subChar[0]) {
                for (int x = 0; x < subChar.length; x++) {
                    if (x + y <= originChar.length - 1 && originChar[y + x] == subChar[x] && x >= subChar.length - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}