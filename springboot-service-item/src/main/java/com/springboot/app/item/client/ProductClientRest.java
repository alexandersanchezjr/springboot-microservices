package com.springboot.app.item.client;

import com.springboot.app.commons.model.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "service-product")
public interface ProductClientRest {

    @GetMapping("/list")
    public List<Product> list();

    @GetMapping("/show/{id}")
    public Product detail(@PathVariable Long id);

    @PostMapping("/create")
    public Product create(@RequestBody() Product product);

    @PutMapping("/edit/{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id);
}
