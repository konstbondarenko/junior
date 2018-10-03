package ru.kbond.sql.store.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class describes the data model for creating the xml file.
 *
 * @author kbondarenko
 * @version 1
 * @since 03.10.2018
 */
@XmlRootElement(name = "entries")
public class EntryList {
    private List<Entry> entry;

    public EntryList() {
    }

    public EntryList(List<Entry> entry) {
        this.entry = entry;
    }

    @XmlElement
    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
