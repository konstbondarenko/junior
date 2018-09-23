package ru.kbond.trackerjdbc.start;

/**
 * Class MenuOutException - используется для генерации ошибки
 * при выборе несуществующего пункта меню.
 * @author kbondarenko
 * @since 01.07.2017
 * @version 1
 */
public class MenuOutException extends RuntimeException {
    /**
     * Метод задающий сообщение для выбрасываемого исключения.
     * @param msg - сообщение.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
