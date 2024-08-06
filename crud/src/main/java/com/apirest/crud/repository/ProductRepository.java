package com.apirest.crud.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.apirest.crud.model.Product;

@Repository
public class ProductRepository {

    private ArrayList<Product> products = new ArrayList<Product>(); 
    private Integer lastId = 0;

    /**
     * METODO PARA RETORNAR UMA LISTA DE PRODUTOS
     * @return LISTA DE PRODUTOS
    */
    public List<Product> getAll() {
        return products;
    } 

    /**
     * METODO PARA BUSCAR UM PRODUTO POR ID
     * @param id do produto que será localizado.
     * @return RETORNA UM PRODUTO POR ID CASO TENHA ENCONTRADO.
    */
    public Optional<Product> getById(Integer id) {
        return products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }
}
