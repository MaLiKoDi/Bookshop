package com.BookShopSpring.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookShopSpring.bookshop.models.Publisher;


@Repository
public interface IPublisherRepository extends JpaRepository<Publisher, Integer>{

}
