package com.BookShopSpring.bookshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookShopSpring.bookshop.models.Book;
import com.BookShopSpring.bookshop.repositories.IBookRepository;

@Service
public class BookServiceImpl implements IBookService{
	
	@Autowired
	private IBookRepository bookRepository;

	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBook() {
		List<Book> allBook = bookRepository.findAll();
		return allBook;
	}

	@Override
	public Book getBookById(Integer id) {
		Book book = bookRepository.findById(id).orElse(null);
		return book;
	}

	@Override
	public void updateBook(Book book) {
		this.saveBook(book);
		
	}

	@Override
	public void deleteBook(Integer id) {
		
		bookRepository.deleteById(id);
		
	}

	
	

}
