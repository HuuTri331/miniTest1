package com.codegym.book.service.impl;


import com.codegym.book.model.Book;
import com.codegym.book.model.Category;
import com.codegym.book.repository.IBookRepository;
import com.codegym.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository iBookRepository;

    @Override
    public Iterable<Book> findAll() {
        return iBookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        iBookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return iBookRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iBookRepository.deleteById(id);
    }

    @Override
    public Iterable<Book> findAllByProvince(Category category) {
        return iBookRepository.findAllByProvince(category);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return iBookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findAllByFirstNameContaining(Pageable pageable, String name) {
        return iBookRepository.findAllByFirstNameContaining(pageable, name);
    }
}