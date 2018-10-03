package ru.kbond.sql.store.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class describes the data model for creating the xml file.
 *
 * @author kbondarenko
 * @version 1
 * @since 03.10.2018
 */
@XmlRootElement(name = "entry")
public class Entry {
    private int field;

    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    @XmlAttribute
    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
