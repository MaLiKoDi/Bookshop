package com.BookShopSpring.bookshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookShopSpring.bookshop.models.Author;
import com.BookShopSpring.bookshop.repositories.IAuthorRepository;

@Service
public class AuthorServiceImpl implements IAuthorService {

	@Autowired
	private IAuthorRepository authorRepository;
	
	@Override
	public void saveAuthor(Author author) {
		authorRepository.save(author);
		
	}

	@Override
	public List<Author> getAllAuthor() {
		List<Author> allAuthor = authorRepository.findAll();
		return allAuthor;
	}

	@Override
	public Author getAuthorById(Integer id) {
		Author author = authorRepository.findById(id).orElse(null);
		return author;
	}

	@Override
	public void updateAuthor(Author client) {
		this.saveAuthor(client);
	}

	@Override
	public void deleteAuthor(Integer id) {
		authorRepository.deleteById(id);
		
	}

}
