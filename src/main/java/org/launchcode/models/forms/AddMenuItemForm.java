package org.launchcode.models.forms;


import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Controller
@RequestMapping("menu")
public class AddMenuItemForm {

    @ManyToOne
    private Menu menu;

    @ManyToMany(mappedBy = "cheeses")
    private Iterable<Cheese> cheeses;

//    public AddMenuItemForm(String name, String menuItem) {
//        this.name = name;
//        this.menuitem = menuitem;
//
//    }

    @NotNull
    private int menuId;

    @NotNull
    @Size(min=3, max=15)
    private String name;

@NotNull
    private int cheeseId;

    public AddMenuItemForm() {
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public AddMenuItemForm(Iterable<Cheese> cheeses, Menu menu) {
        this.menu = menu;
        this.cheeses = cheeses;
    }
}
