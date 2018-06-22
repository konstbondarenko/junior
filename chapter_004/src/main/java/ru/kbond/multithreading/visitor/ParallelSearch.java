package ru.kbond.multithreading.visitor;

import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The class searches for the word in the specified
 * directory in files with the specified extension.
 *
 * @author kbondarenko
 * @version 1
 * @since 17.06.2018
 */
@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    /**
     * A flag indicating the end of the search thread.
     */
    private volatile boolean finishSearch = true;
    /**
     * A flag indicating the end of the reading thread.
     */
    private volatile boolean finishRead = false;
    /**
     * The queue contains the paths of the found files.
     */
    private final Queue<String> files = new LinkedBlockingQueue<>();
    /**
     * List contains the file paths with the word found.
     */
    private final List<String> paths = new CopyOnWriteArrayList<>();

    /**
     * Constructor.
     *
     * @param root directories where you need to search for files.
     * @param text text you want to find.
     * @param exts list of extensions.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * The method starts the search and read streams.
     */
    public void init() {
        Thread search = new Thread() {
            @Override
            public void run() {
                try {
                    Files.walkFileTree(Paths.get(root), new MyFileVisitor(exts));
                    Thread.sleep(200);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                finishSearch = false;
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                while (finishSearch) {
                    while (!files.isEmpty()) {
                        try {
                            String filesPath = files.poll();
                            List<String> allLines = Files.readAllLines(
                                    Paths.get(filesPath), Charset.forName("ISO-8859-1"));
                            for (String allLine : allLines) {
                                if (allLine.contains(text)) {
                                    paths.add(filesPath);
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                finishRead = false;
            }
        };
        search.start();
        read.start();
        try {
            search.join();
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method displays a list of found file paths matching the query.
     *
     * @return list of found file paths matching the query.
     */
    public List<String> result() throws InterruptedException {
        synchronized (this) {
            while (this.finishRead) {
                this.wait();
            }
            return this.paths;
        }
    }

    /**
     * Override simple visitor.
     */
    private class MyFileVisitor extends SimpleFileVisitor<Path> {
        private List<String> exts;

        /**
         * Constructor.
         */
        public MyFileVisitor(List<String> exts) {
            this.exts = exts;
        }

        /**
         * Invoked for a file in a directory.
         */
        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
            for (String ext : exts) {
                if (path.toString().endsWith(ext)) {
                    files.add(path.toString());
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

