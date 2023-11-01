package com.BookShopSpring.bookshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BookShopSpring.bookshop.models.Author;


@Service
public interface IAuthorService {
	
	public void saveAuthor(Author author);
	
	public List<Author> getAllAuthor();
	
	public Author getAuthorById(Integer id);
	
	public void updateAuthor(Author client);
	
	public void deleteAuthor(Integer id);

}
