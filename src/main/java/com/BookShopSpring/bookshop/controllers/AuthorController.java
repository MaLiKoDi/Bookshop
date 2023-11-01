package com.BookShopSpring.bookshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.BookShopSpring.bookshop.models.Author;
import com.BookShopSpring.bookshop.services.AuthorServiceImpl;


@RestController
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorServiceImpl authorService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createAuthor(@RequestBody Author author) {

		try {
			authorService.saveAuthor(author);
			return new ResponseEntity<>("Autor creado", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Author>> getAllAuthors() {
		List<Author> allAuthors = authorService.getAllAuthor();

		if (!allAuthors.isEmpty()) {
			return new ResponseEntity<>(allAuthors, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable("id") Integer author_id) {
		Author author = authorService.getAuthorById(author_id);

		if (author != null) {
			return new ResponseEntity<>(author, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
		try {
			authorService.updateAuthor(author);
			Author authorUpdated = authorService.getAuthorById(author.getId());

			if (authorUpdated != null) {
				return new ResponseEntity<>(authorUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/register/{author_id}")
	public ResponseEntity<String> registerAuthor(@PathVariable Integer author_id) {
		try {
			Author author = authorService.getAuthorById(author_id);
			if (author != null && author.getRegistered() == false) {
				author.setRegistered(true);
				authorService.updateAuthor(author);
				return new ResponseEntity<>("Autor dado de alta", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe autor con ese ID o ya fue dado de alta", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping("/remove/{author_id}")
	public ResponseEntity<String> removeAuthor(@PathVariable Integer author_id) {
		try {
			Author author = authorService.getAuthorById(author_id);
			if (author != null && author.getRegistered() != false) {
				author.setRegistered(false);
				authorService.updateAuthor(author);
				return new ResponseEntity<>("Autor dado de baja", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe autor con ese ID o ya fue dado de baja", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/delete/{author_id}")
	public ResponseEntity<String> deleteAuthorById(@PathVariable Integer author_id) {
		try {
			Author author = authorService.getAuthorById(author_id);
			if (author != null) {
				authorService.deleteAuthor(author_id);
				return new ResponseEntity<>("Autor borrado", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe Autor con ese ID", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
