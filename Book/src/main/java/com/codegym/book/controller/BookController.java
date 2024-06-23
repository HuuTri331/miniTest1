package com.codegym.book.controller;


import com.codegym.book.model.Book;
import com.codegym.book.model.Category;
import com.codegym.book.service.IBookService;
import com.codegym.book.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categoryService")
    public Iterable<Category> listProvinces() {
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView listCustomer(Pageable pageable) {
        Page<Book> books = bookService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView listCustomersSearch(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<Book> books;
        if(search.isPresent()){
            books = bookService.findAllByFirstNameContaining(pageable, search.get());
        } else {
            books = bookService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("book") Book book,
                         RedirectAttributes redirectAttributes) {
        bookService.save(book);
        redirectAttributes.addFlashAttribute("message", "Create new book successfully");
        return "redirect:/books";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/book/update");
            modelAndView.addObject("book", book.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("book") Book book,
                         RedirectAttributes redirect) {
        bookService.save(book);
        redirect.addFlashAttribute("message", "Update book successfully");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        bookService.remove(id);
        redirect.addFlashAttribute("message", "Delete book successfully");
        return "redirect:/books";
    }
}
