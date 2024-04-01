package com.springboot.app.products.models.dao;

import com.springboot.app.products.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Long>{
}
