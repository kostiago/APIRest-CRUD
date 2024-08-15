package com.apirest.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest.crud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>{

    
}
