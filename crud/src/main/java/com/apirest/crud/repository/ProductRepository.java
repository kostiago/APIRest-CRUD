package com.apirest.crud.repository;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.apirest.crud.model.Product;
import com.apirest.crud.model.exception.ResourceNotFoundException;

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

    /**
     * METODO PARA ADICIONAR UM NOVO PRODUTO.
     * @param product QUE SERÁ ADICIONADO.
     * @return UM PRODUTO QUE FOI ADICIONADO.
     */
    public Product newProduct (Product product) {

        lastId++;

        product.setId(lastId);
        products.add(product);

        return product;
    }
    
    /**
     * METODO PARA DELETAR UM PRODUTO POR ID.
     * @param id DO PRODUTO DELETADO.
     */
    public void delete(Integer id) {
        products.removeIf(product -> product.getId() == id);
    }

    /**
     * METODO PARA ATUALIZAR PRODUTO POR ID
     * @param product QUE SERÀ ATUALIZADO 
     * @return O PRODUTO ATUALIZADO
     */
    public Product updateProduct (Product product) {
        
        //Buscar produto na lista
        Optional<Product> productFound = getById(product.getId());

        if (productFound.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        //Remover o produto antigo
        delete(product.getId());

        //adicionar o novo produto atualizado
        products.add(product);

        return product;
    }
}
