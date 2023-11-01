package com.BookShopSpring.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookShopSpring.bookshop.models.Book;


@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {

}
