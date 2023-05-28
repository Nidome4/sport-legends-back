package org.software.user;

import java.util.List;

public class UserList {
    private List<User> items;

    public UserList(List<User> items) {
        this.items = items;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    public UserList() {
    }
}
