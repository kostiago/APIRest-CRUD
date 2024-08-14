package com.apirest.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.crud.model.Product;
import com.apirest.crud.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Retorna uma lista de produtos
     */
    public List<Product> getAll () {
        return productRepository.getAll();
    }

    /**
     * Metodo de retorno de produto encontrado pelo ID
     * @param id do produto a ser localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<Product> getByID(Integer id) {
        return productRepository.getById(id);
    }

    /**
     * Metodo para adicionar um novo produto
     * @param product que será adicionado.
     * @return retorna o produto que foi adicionado.
     */
    public Product newProduct( Product product) {
        return productRepository.newProduct(product);
    }

    /**
     * Metodo para deletar produto pelo ID
     * @param id do produto que será deletado
     */
    public void delete (Integer id) {
         productRepository.delete(id);
    }

    /**
     * Metodo para atualizar um produto pelo ID.
     * @param id do produto que sera atualizado
     * @param product que será atualizado.
     * @return Retorna o produto atualizado.
     */
    public Product updateProduct (Integer id, Product product){
        product.setId(id);
        return productRepository.updateProduct(product);
    }

}
