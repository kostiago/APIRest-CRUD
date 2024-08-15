package com.apirest.crud.view.model;


public class ProductResponse {
     //#region Atributos
    
    private Integer id;

    private String name;

    private Integer quantity;

    private Double price;
    
    private String description;
    //#endregion

    //#region Getters/Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //#endregion
    
}
