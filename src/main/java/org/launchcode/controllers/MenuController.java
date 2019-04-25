package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value= "")
    public String index(Model model) {

        model.addAttribute("items", menuDao.findAll());
        model.addAttribute("title", "Menus");

        return "menu/index";

    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());

        return "menu/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Menu newMenu, Errors errors,
                      Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("name", "Add Menu");
            return "menu/add";
        }

            menuDao.save(newMenu);
            return "redirect:view/" + newMenu.getId();


    }

    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String displayviewMenuForm(@PathVariable int menuId, Model model) {
        Menu menu = menuDao.findOne(menuId);
        model.addAttribute("menu", menu);
        return "menu/view";
    }

    // NOTE: Everything above this line works (Aleesha thinks so at least)

    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String viewAllMenus(Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);

        AddMenuItemForm form = new AddMenuItemForm(cheeseDao.findAll(), menu);

        // When we load this page, we see an empty dropdown. We should see all the cheeses
        // th:object, th:field stuff is probably going on
        // I think I fixed this dropdown problem (Maggie)
        // Still get an error at /menu/add-item

        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", form);
        // I changed this to "form, form and now it doesn't work and I
        // don't remember what it was before
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(@ModelAttribute @Valid AddMenuItemForm form, Errors errors,
                          Model model) {
//
        if (errors.hasErrors()) {
//        //    Menu menu = form.getMenu();
            model.addAttribute("form", form);
//        //    return "/menu/add-item";
        }
            Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
            Menu theMenu = menuDao.findOne(form.getMenuId());
            theMenu.addItem(theCheese);
            menuDao.save(theMenu);
//
            return "redirect:/menu/view/" + theMenu.getId();
//        //We actually need to get the cheese using the form's cheeseId
//        //Likewise, we need the menu
//        //Add cheese to menu and save
//        //Return /menu/view/menuId where menuId comes from the form
//        //return "";
      }



}

