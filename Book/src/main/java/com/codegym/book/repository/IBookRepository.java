package com.codegym.book.repository;

import com.codegym.book.model.Book;
import com.codegym.book.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IBookRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findAllByProvince(Category Category);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByFirstNameContaining(Pageable pageable, String name);
}
