package ru.kbond.sql.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * The class converts the xml file according to the specified scheme xslt.
 *
 * @author kbondarenko
 * @version 1
 * @since 03.10.2018
 */
public class ConvertXSQT {
    private static final Logger LOG = LoggerFactory.getLogger(ConvertXSQT.class);
    /**
     * Method converts the xml file according to the specified scheme xslt.
     *
     * @param source file to convert.
     * @param dest output file.
     * @param scheme schema file.
     */
    public void convert(File source, File dest, File scheme) {
        StreamSource streamSource = new StreamSource(source);
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer(new StreamSource(scheme));
            t.transform(streamSource, new StreamResult(dest));
        } catch (TransformerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
