package com.example.product.productlist.web;

import javax.validation.constraints.NotBlank;

public class ProductDTO {
    private Long id;
    @NotBlank(message = "Product name is mandatory")
    private String name;

    @NotBlank(message = "Product category is mandatory")
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
