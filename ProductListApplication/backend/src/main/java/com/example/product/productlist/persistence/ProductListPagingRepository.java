package com.example.product.productlist.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductListPagingRepository extends PagingAndSortingRepository<ProductList, Long> {

    List<ProductList> findByProductName(String productName, Pageable pageable);
}