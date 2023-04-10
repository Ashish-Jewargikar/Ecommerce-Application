package com.example.Ecommerce_Application.Repository;

import com.example.Ecommerce_Application.Enum.ProductCategory;
import com.example.Ecommerce_Application.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByProductCategory(ProductCategory productCategory);
}