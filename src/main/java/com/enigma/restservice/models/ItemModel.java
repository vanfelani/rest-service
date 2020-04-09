package com.enigma.restservice.models;

import com.enigma.restservice.validation.annotations.MinLength;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ItemModel {
    private Integer id;

    @MinLength(3)
    @NotBlank(message = "{name.not.blank}")
//    @Email(message = "{invalid.email.format}")
    private String name;

    public ItemModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ItemModel() {
    }
    

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
    

    @Override
    public String toString() {
        return "ItemModel{" + "id=" + id + ", name=" + name + '}';
    }
    
    
    
}
