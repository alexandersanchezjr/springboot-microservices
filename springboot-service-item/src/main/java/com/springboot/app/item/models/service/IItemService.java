package com.springboot.app.item.models.service;

import com.springboot.app.item.models.Item;
import com.springboot.app.commons.model.entity.Product;

import java.util.List;

public interface IItemService {

    public List<Item> findAll();
    public Item findById(Long id, Integer quantity);

    public Product save(Product product);

    public Product update(Product product, Long id);

    public void delete(Long id);

}
