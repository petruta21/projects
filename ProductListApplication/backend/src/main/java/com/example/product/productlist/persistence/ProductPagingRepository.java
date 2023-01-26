package com.example.product.productlist.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findByProductName(String productName, Pageable pageable);
}


