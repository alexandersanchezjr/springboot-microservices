package com.springboot.app.item.models.service;

import com.springboot.app.item.models.Item;
import com.springboot.app.commons.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("serviceRestTemplate")
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

    @Override
    public Product save(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = restTemplate.exchange("http://service-product/create", HttpMethod.POST, body, Product.class);
        return response.getBody();
    }

    @Override
    public Product update(Product product, Long id) {
        Map<String, String> pathVariables = Map.of("id", id.toString());
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = restTemplate.exchange("http://service-product/edit/{id}", HttpMethod.PUT, body, Product.class, pathVariables);
        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables = Map.of("id", id.toString());
        restTemplate.delete("http://service-product/delete/{id}", pathVariables);
    }
}
