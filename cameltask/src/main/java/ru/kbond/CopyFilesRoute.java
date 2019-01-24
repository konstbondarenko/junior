package ru.kbond;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;
import org.apache.log4j.Logger;

/**
 * Класс копирует файл ил папки А в папку В.
 *
 * @author kbondarenko
 * @version 1
 * @since 24.01.2019
 */
public class CopyFilesRoute extends RouteBuilder {
    private Logger logger = Logger.getLogger(CopyFilesRoute.class);
    private String from;
    private String to;

    public CopyFilesRoute(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void configure() {
        from("file:" + this.from)
                .process(e -> {
                    String fileName = (String) e.getIn().getHeader(Exchange.FILE_NAME);
                    logger.info("received file " + fileName + " from " + this.from);
                })
                .to("file:" + this.to)
                .process(e -> {
                    String fileName = (String) e.getIn().getHeader(Exchange.FILE_NAME);
                    logger.info("file " + fileName + " sent to " + this.to);
                });
    }
}

class StartSpot {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new CopyFilesRoute("F:/test1/", "F:/test2/"));
        main.run();
    }
}
