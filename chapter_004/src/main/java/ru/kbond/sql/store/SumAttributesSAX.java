package ru.kbond.sql.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * The class parses the input xml file.
 *
 * @author kbondarenko
 * @version 1
 * @since 03.10.2018
 */
public class SumAttributesSAX extends DefaultHandler {
    private static final Logger LOG = LogManager.getLogger(SumAttributesSAX.class.getName());
    private int sumAttrib;
    private File file;

    /**
     * Constructor.
     *
     * @param file incoming xml file.
     */
    public SumAttributesSAX(File file) {
        this.file = file;
    }

    /**
     * Getter.
     *
     * @return parsing result.
     */
    public int getSumAttrib() {
        return sumAttrib;
    }

    /**
     * The method displays the arithmetic sum of the values of all attributes.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("entry")) {
            this.sumAttrib += Integer.parseInt(attributes.getValue("href"));
        }
    }

    /**
     * The method starts the file parsing.
     */
    public void init() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(this.file, this);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
