package com.springboot.app.products.controllers;

import com.springboot.app.products.models.entity.Product;
import com.springboot.app.products.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public List<Product> list(){
        return productService.findAll();
    }

    @GetMapping("/show/{id}")
    public Product show(@PathVariable Long id) throws InterruptedException {
        if (id.equals(10L))
            throw new IllegalStateException("This product can not be shown");
        if (id.equals(7L))
            TimeUnit.SECONDS.sleep(5L);
        return productService.findById(id);
    }
}
