package com.codegym.book.controller;

import com.codegym.book.model.Book;
import com.codegym.book.model.Category;
import com.codegym.book.service.IBookService;
import com.codegym.book.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categorys")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBookService bookService;

    @GetMapping
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("/category/list");
        Iterable<Category> provinces = categoryService.findAll();
        modelAndView.addObject("categorys", provinces);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("province") Category category,
                         RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Create new province successfully");
        return "redirect:/categorys";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Category> province = categoryService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/category/update");
            modelAndView.addObject("category", province.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("category") Category category,
                         RedirectAttributes redirect) {
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Update province successfully");
        return "redirect:/categorys";
    }

    @GetMapping("/view-category/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Category> provinceOptional = categoryService.findById(id);
        if(!provinceOptional.isPresent()){
            return new ModelAndView("/error_404");
        }

        Iterable<Book> books = bookService.findAllByProvince(provinceOptional.get());

        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }
}
