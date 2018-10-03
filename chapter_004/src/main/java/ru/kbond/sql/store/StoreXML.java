package ru.kbond.sql.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kbond.sql.store.pojo.Entry;
import ru.kbond.sql.store.pojo.EntryList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * The class stores a list of "Entry" objects in the form of a xml table.
 *
 * @author kbondarenko
 * @version 1
 * @since 03.10.2018
 */
public class StoreXML {
    private static final Logger LOG = LoggerFactory.getLogger(StoreXML.class);
    private File target;

    /**
     * Constructor.
     *
     * @param target the file in which the table will be saved.
     */
    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Method stores a list of "Entry" objects in the form of a xml table
     *
     * @param list list of "Entry" objects.
     */
    public void save(List<Entry> list) {
        EntryList entryList = new EntryList(list);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EntryList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entryList, this.target);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
