package com.apirest.crud.view.model;



public class ProductRequest {
     //#region Atributos
    
   
    private String name;

    private Integer quantity;

    private Double price;
    
    private String description;
    //#endregion

    //#region Getters/Setters
    
   

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
