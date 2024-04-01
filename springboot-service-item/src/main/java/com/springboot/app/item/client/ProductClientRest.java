package com.springboot.app.item.client;

import com.springboot.app.item.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-product")
public interface ProductClientRest {

    @GetMapping("/list")
    public List<Product> list();

    @GetMapping("/show/{id}")
    public Product detail(@PathVariable Long id);
}
