package com.BookShopSpring.bookshop.services;

import java.util.List;

import org.springframework.stereotype.Service;


import com.BookShopSpring.bookshop.models.Book;

@Service
public interface IBookService {
	
	public void saveBook(Book book);
	
	public List<Book> getAllBook();
	
	public Book getBookById(Integer id);
	
	public void updateBook(Book book);
	
	public void deleteBook(Integer id);
	
	

}
