package com.example.product.productlist.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RegistrationPagingRepository extends PagingAndSortingRepository<Registration, Long> {

    List<Product> findByProductName(String userName, Pageable pageable);
}
