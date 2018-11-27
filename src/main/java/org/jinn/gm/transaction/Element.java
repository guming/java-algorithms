package org.jinn.gm.transaction;


public class Element {

    private Integer id;
    private String value;

    public Element(String value) {
        this.value = value;
    }

    public Element(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Element{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
