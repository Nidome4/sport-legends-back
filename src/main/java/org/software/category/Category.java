package org.software.category;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int published;
    private String name;
    private String icon;
    @Column(name = "created_at")
    private LocalDateTime  createdAt = LocalDateTime.now();
    @Column(name = "updated_at")
    private LocalDateTime  updatedAt = LocalDateTime.now();

    public Category() {
    }

    public Category(long id, int published, String name, String icon) {
        this.id = id;
        this.published = published;
        this.name = name;
        this.icon = icon;
    }

    public Category(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateCategory(Category category2){
        if(category2.getPublished() != 0){
            this.published = category2.getPublished();
        }
        if(category2.getIcon() != null && category2.getIcon() != ""){
            this.icon = category2.getIcon();
        }
        if(category2.getName() != null && category2.getName() != ""){
            this.name = category2.getName();
        }
    }
}
