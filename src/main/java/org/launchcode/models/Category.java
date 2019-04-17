package org.launchcode.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.security.PublicKey;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Please name the category")
    private String division;

    //private Category division;


    public Category(String name, String division) {

        this.name = name;
        this.division = division;

    }

    public Category() { }


    public int getId() { return id; }

    public String getName() { return name;}

    public void setName(String name) {this.name = name; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    //public CategoryType getType() { return type; }

    //public void setType(CategoryType type) {this.type = type; }


}
