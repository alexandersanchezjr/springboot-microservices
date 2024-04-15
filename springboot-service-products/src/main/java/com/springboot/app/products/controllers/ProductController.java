package com.springboot.app.products.controllers;

import com.springboot.app.commons.model.entity.Product;
import com.springboot.app.products.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product product, @PathVariable Long id){
        Product productDB = productService.findById(id);
        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());
        return productService.save(productDB);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.deleteById(id);
    }
}
