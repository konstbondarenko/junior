package ru.kbond.observer.listener;

import java.io.File;
import java.io.IOException;

/**
 * Слушатель записывающий сообщения в файл.
 *
 * @author kbondarenko
 * @version 1
 * @since 23.01.2019
 */
public class FileWriter implements EventListener {
    private String filePath;

    public FileWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void update(String eventType, String msg) {
        File file = new File(this.filePath);
        java.io.FileWriter fr = null;
        StringBuilder sb = new StringBuilder(msg);
        sb.append("\n");
        try {
            fr = new java.io.FileWriter(file, true);
            fr.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
