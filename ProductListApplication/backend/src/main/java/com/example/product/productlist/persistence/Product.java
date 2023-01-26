package com.example.product.productlist.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "products")
public class Product implements Persistable<Long> {
    @Id
    @Column("id")
    private Long productId;
    @Column("name")
    private String productName;

    @Column("category")
    private String productCategory;

    @Override
    @JsonIgnore
    public Long getId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return productId == null;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
