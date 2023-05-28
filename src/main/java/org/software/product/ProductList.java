package org.software.product;


import java.util.List;

public class ProductList {

    private List<Product> items;

    public ProductList() {
    }

    public ProductList(List<Product> items) {
        this.items = items;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
}
