package com.springboot.app.item.models.service;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Item> findAll() {
        List<Product> products = List.of(restTemplate.getForObject("http://localhost:8001/list", Product[].class));
        return (List<Item>) products.stream().map(product -> new Item(product, 1));
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Map<String, String> pathVariables = Map.of("id", id.toString());
        Product product = restTemplate.getForObject("http://localhost:8001/show/{id}", Product.class, pathVariables);
        return new Item(product, quantity);
    }
}
