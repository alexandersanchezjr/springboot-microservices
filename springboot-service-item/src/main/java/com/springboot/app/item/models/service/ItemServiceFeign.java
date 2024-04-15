package com.springboot.app.item.models.service;

import com.springboot.app.item.client.ProductClientRest;
import com.springboot.app.item.models.Item;
import com.springboot.app.commons.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService {

    @Autowired
    private ProductClientRest clientRest;

    @Override
    public List<Item> findAll() {
        return clientRest.list().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new Item(clientRest.detail(id), quantity);
    }

    @Override
    public Product save(Product product) {
        return clientRest.create(product);
    }

    @Override
    public Product update(Product product, Long id) {
        return clientRest.update(product, id);
    }

    @Override
    public void delete(Long id) {
        clientRest.delete(id);
    }


}
