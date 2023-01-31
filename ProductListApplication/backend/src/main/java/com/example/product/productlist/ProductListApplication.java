package com.example.product.productlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ProductListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductListApplication.class, args);
    }

}
