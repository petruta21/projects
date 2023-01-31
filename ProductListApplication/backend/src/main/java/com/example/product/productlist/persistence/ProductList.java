package com.example.product.productlist.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "product_list")
public class ProductList implements Persistable<Long> {

    @Id
    @Column("list_id")
    private Long productListId;
    @Column("product_name")
    private String productName;

    @Column("amount")
    private String productAmount;

    @Column("bought")
    private Boolean productBought;

    @Override
    @JsonIgnore
    public Long getId() {
        return productListId;
    }

    public void setProductListId(Long productListId) {
        this.productListId = productListId;
    }

    public boolean isNew() {
        return productListId == null;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public Boolean getProductBought() {
        return productBought;
    }

    public void setProductBought(Boolean productBought) {
        this.productBought = productBought;
    }

}
