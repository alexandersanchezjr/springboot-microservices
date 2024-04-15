package com.springboot.app.products.models.dao;

import com.springboot.app.commons.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDao extends CrudRepository<Product, Long>{
}
