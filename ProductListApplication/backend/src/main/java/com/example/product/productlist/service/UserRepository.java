package com.example.product.productlist.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email);
}
