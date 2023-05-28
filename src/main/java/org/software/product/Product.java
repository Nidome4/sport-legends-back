package org.software.product;

import org.software.category.Category;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long pricing;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "short_description")
    private String shortDescription;
    @Column(name = "long_description")
    private String longDescription;
    private String icon;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Product() {
    }

    public Product(long id, String name, long pricing, Category category, String shortDescription, String longDescription, String icon, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.pricing = pricing;
        this.category = category;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.icon = icon;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPricing() {
        return pricing;
    }

    public void setPricing(long pricing) {
        this.pricing = pricing;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void updateAll(Product product2){
        if(product2.getName() != null && product2.getName() !=""){
            this.name = product2.getName();
        }
        if(product2.getPricing() != 0){
            this.pricing = product2.getPricing();
        }
        if(product2.getShortDescription() != null && product2.getShortDescription() !=""){
            this.shortDescription = product2.getShortDescription();
        }
        if(product2.getLongDescription() != null && product2.getLongDescription() !=""){
            this.longDescription = product2.getLongDescription();
        }
        if (product2.getIcon() != null && product2.getIcon() != ""){
            this.icon = product2.getIcon();
        }
    }
}
