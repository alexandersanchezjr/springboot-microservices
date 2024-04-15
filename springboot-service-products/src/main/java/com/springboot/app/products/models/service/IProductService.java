package com.springboot.app.products.models.service;

import com.springboot.app.commons.model.entity.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();
    public Product findById(Long id);

    public Product save(Product product);

    public void deleteById(Long id);

}
