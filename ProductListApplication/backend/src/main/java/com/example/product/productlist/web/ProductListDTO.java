package com.example.product.productlist.web;

import javax.validation.constraints.NotBlank;

public class ProductListDTO {

    private Long id;
    @NotBlank(message = "Product name is mandatory")
    private String name;
    private String amount;

    //  @NotBlank(message = "Product category is mandatory")
    private boolean bought;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

}

