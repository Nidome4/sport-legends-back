package org.software.category;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


public class CategoryList {
    private List<Category> items;

    public CategoryList() {
    }
    public CategoryList(List<Category> items) {
        this.items = items;
    }

    public List<Category> getItems() {
        return items;
    }
}

