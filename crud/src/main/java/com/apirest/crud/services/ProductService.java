package com.apirest.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.crud.model.Product;
import com.apirest.crud.model.exception.ResourceNotFoundException;
import com.apirest.crud.repository.ProductRepository;
import com.apirest.crud.shared.ProductDTO;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Retorna uma lista de produtos
     */
    public List<ProductDTO> getAll () {

        List<Product> products =  productRepository.findAll();

        return products.stream()
        .map(product -> new ModelMapper().map(product, ProductDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Metodo de retorno de produto encontrado pelo ID
     * @param id do produto a ser localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<ProductDTO> getById(Integer id) {
        
        //Obtendo optional de produto pelo id.
        Optional<Product> product = productRepository.findById(id);
        
        //Lança uma exception caso não encontre o id
        if(product.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: " + id + "não encontrado");
        }

        //Converte o optional de produto em um ProdutoDTO
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);

        //Cria e retorna um optional de DTO.
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar um novo produto
     * @param product que será adicionado.
     * @return retorna o produto que foi adicionado.
     */
    public ProductDTO newProduct( ProductDTO productDTO) {
    
        //Remove o id para conseguir fazer o cadastro.
        productDTO.setId(null);

        //Cria um objeto de mapeamento 
        ModelMapper mapper = new ModelMapper();

        //Converte o produtoDTO em um produto
        Product product = mapper.map(productDTO, Product.class);

        //Salva o produto no banco
        product = productRepository.save(product);

        productDTO.setId(product.getId());

        //Retorna o produto atualizado
        return productDTO;

    }

    /**
     * Metodo para deletar produto pelo ID
     * @param id do produto que será deletado
     */
    public void delete (Integer id) {
        
        //Verifica se o produto existe
        Optional<Product> product = productRepository.findById(id);

        //Exception, caso o produto não exista
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel encontrar o produto com id: " + id + " Produto não cadastro no banco de dados.");
        }

        //Deleta o produto pelo id
         productRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar um produto pelo ID.
     * @param id do produto que sera atualizado
     * @param product que será atualizado.
     * @return Retorna o produto atualizado.
     */
    public ProductDTO updateProduct (Integer id, ProductDTO productDTO){
       
        //Atualizar o id para o ProdutoDTO.
        productDTO.setId(id);

        //Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        //Converter um produtoDTO em um produto.
        Product product = mapper.map(productDTO, Product.class);

        //Atualizar o produto no banco de dados.
        productRepository.save(product);

        //Retonar o ProdutoDTO atualizado.
        return productDTO;

    }

}
