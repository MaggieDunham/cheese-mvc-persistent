package org.launchcode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;



    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Categories");
        model.addAttribute("name");
        model.addAttribute("category");

        return "category/index";

    }
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addNewCategory(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());
        model.addAttribute("category", category);
        return "category/add";

    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddNewCategory(@ModelAttribute @Valid Category category, Error errors) {

        if (errors.hasErrors()) {
            model.addAttribute( "title", "Add Category");
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";
    }



}
