package com.apirest.crud.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.crud.services.ProductService;
import com.apirest.crud.shared.ProductDTO;
import com.apirest.crud.view.model.ProductRequest;
import com.apirest.crud.view.model.ProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity <List<ProductResponse>> getAll () {
        List<ProductDTO> product = productService.getAll();

        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> res = product.stream()
        .map(productDTO -> mapper.map(productDTO, ProductResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getById(@PathVariable Integer id) {
        
        Optional<ProductDTO> dto = productService.getById(id);
        ProductResponse product = new ModelMapper().map(dto.get(), ProductResponse.class);

        return new ResponseEntity<>(Optional.of(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> newProduct(@RequestBody ProductRequest productRequest) {
        
        ModelMapper mapper = new ModelMapper();

        ProductDTO productDTO = mapper.map(productRequest, ProductDTO.class);
        productDTO = productService.newProduct(productDTO);

        return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct (@RequestBody ProductRequest productReq, @PathVariable Integer id) {
       
        ModelMapper mapper = new ModelMapper();
        ProductDTO productDTO = mapper.map(productReq, ProductDTO.class);
        productDTO = productService.updateProduct(id, productDTO);

        return new ResponseEntity<>(
            mapper.map(productDTO, ProductResponse.class),
            HttpStatus.OK
        );
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Integer id) {
        productService.delete(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
