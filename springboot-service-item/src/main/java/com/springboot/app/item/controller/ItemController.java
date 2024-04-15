package com.springboot.app.item.controller;

import com.springboot.app.item.models.Item;
import com.springboot.app.commons.model.entity.Product;
import com.springboot.app.item.models.service.IItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    private Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;

    @GetMapping("/list")
    public List<Item> list(@RequestParam(name="name", required = false) String name, @RequestHeader(name="X-Request-Name", required = false) String requestName) {
        System.out.println(name);
        System.out.println(requestName);
        return itemService.findAll();
    }

    @CircuitBreaker(name = "item", fallbackMethod = "fallback")
    @GetMapping("/show/{id}/quantity/{quantity}")
    public Item detail(@PathVariable Long id,@PathVariable Integer quantity) {
        return itemService.findById(id, quantity);
    }

//    @GetMapping("/show/{id}/quantity/{quantity}")
//    public Item detail(@PathVariable Long id,@PathVariable Integer quantity) {
//        return circuitBreakerFactory.create("item").run(() -> itemService.findById(id, quantity), e -> fallback(id, quantity));
//    }


    @GetMapping("/get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
        Map<String, String> json = Map.of("port", port);

        logger.info("Port: " + port);
        return ResponseEntity.ok(json);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return itemService.save(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product product, @PathVariable Long id) {
        return itemService.update(product, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

    public Item fallback(Long id, Integer quantity, Throwable e) {

        logger.info("Fallback method");
        logger.error(e.getMessage());

        Item item = new Item();
        Product product = new Product();
        product.setId(id);
        product.setName("Default product");
        product.setPrice(500.00);

        item.setProduct(product);
        item.setQuantity(quantity);
        return item;
    }
}
