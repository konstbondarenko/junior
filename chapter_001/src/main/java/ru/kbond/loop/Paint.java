package ru.kbond.loop;

/**
 * Class Paint рисует пирамиду из символа "^".
 * @author kbondarenko
 * @since 20.03.2017
 * @version 1
 */
public class Paint {
	/**
	 * Метод рисующий пирамиду.
	 * @param h число, задающее высоту пирамиды.
	 * @return результат
	 */
    public String piramid(int h) {
		StringBuilder pirPain = new StringBuilder();
        int c = 1;
        for (int i = 0; i < c * h; i++) {
            for (int j = 1; j < h; j++) {
                pirPain.append(" ");
            }
            for (int x = 0; x < c; x++) {
                pirPain.append("^");
            }
            h -= 1;
            c += 2;
                pirPain.append(System.getProperty("line.separator"));
        }
        return pirPain.toString();
    }
}