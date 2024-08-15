package com.apirest.crud.view.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.crud.model.Product;
import com.apirest.crud.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List <Product> getAll () {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PostMapping
    public Product newProduct(@RequestBody Product product) {
        return productService.newProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct (@RequestBody Product product, @PathVariable Integer id) {
        return productService.updateProduct(id, product);
    }
    
    @DeleteMapping("/{id}")
    public String delete (@PathVariable Integer id) {
        productService.delete(id);
        return "Produto com o id:" + id + "foi deletado com sucesso";
    }
}
