package com.codegym.book.repository;

import com.codegym.book.model.Book;
import com.codegym.book.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category, Long> {
}
