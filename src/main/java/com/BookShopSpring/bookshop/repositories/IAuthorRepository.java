package com.BookShopSpring.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookShopSpring.bookshop.models.Author;


@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {

}
