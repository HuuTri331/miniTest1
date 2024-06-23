package com.codegym.book.service;

import com.codegym.book.model.Book;
import com.codegym.book.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IBookService extends IGenerateService<Book>{
    Iterable<Book> findAllByProvince(Category province);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByFirstNameContaining(Pageable pageable, String name);
}
