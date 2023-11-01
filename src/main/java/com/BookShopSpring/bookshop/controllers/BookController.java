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


import com.BookShopSpring.bookshop.models.Book;
import com.BookShopSpring.bookshop.services.BookServiceImpl;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookServiceImpl bookService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createBook(@RequestBody Book book) {

		try {
			bookService.saveBook(book);
			return new ResponseEntity<>("Libro creado", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("No se pudo crear Libro", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> allBooks = bookService.getAllBook();

		if (!allBooks.isEmpty()) {
			return new ResponseEntity<>(allBooks, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") Integer book_id) {
		Book book = bookService.getBookById(book_id);

		if (book != null) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/update")
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		try {
			bookService.updateBook(book);
			Book bookUpdated = bookService.getBookById(book.getId());

			if (bookUpdated != null) {
				return new ResponseEntity<>(bookUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/return/{book_id}")
	public ResponseEntity<String> returnBook(@PathVariable Integer book_id) {
		try {
			Book book = bookService.getBookById(book_id);
			if (book != null && book.getRegistered() != false && book.getCopy()>=book.getBorrowedCopy()+1) {
				book.setRemainingCopy(book.getRemainingCopy()+1);
				book.setBorrowedCopy(book.getBorrowedCopy()-1);
				bookService.updateBook(book);
				return new ResponseEntity<>("Libro devuelto", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Id de Libro equivocado o no pertenece a la libreria", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PutMapping("/borrow/{book_id}")
	public ResponseEntity<String> borrowBook(@PathVariable Integer book_id) {
		try {
			Book book = bookService.getBookById(book_id);
			if (book != null && book.getRegistered() != false && book.getRemainingCopy()>0) {
				book.setRemainingCopy(book.getRemainingCopy()-1);
				book.setBorrowedCopy(book.getBorrowedCopy()+1);
				bookService.updateBook(book);
				return new ResponseEntity<>("Libro prestado", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe Libro con ese ID o no quedan copias", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PutMapping("/inStock/{book_id}")
	public ResponseEntity<String> bookInStock(@PathVariable Integer book_id) {
		try {
			Book book = bookService.getBookById(book_id);
			if (book != null && book.getRegistered() == false) {
				book.setRegistered(true);
				bookService.updateBook(book);
				return new ResponseEntity<>("Libro dado de alta", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe Libro con ese ID o ya fue dado de alta", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PutMapping("/outStock/{book_id}")
	public ResponseEntity<String> bookOutStock(@PathVariable Integer book_id) {
		try {
			Book book = bookService.getBookById(book_id);
			if (book != null && book.getRegistered() != false) {
				book.setRegistered(false);
				bookService.updateBook(book);
				return new ResponseEntity<>("Libro dado de baja", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe Libro con ese ID o ya fue dado de baja", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/delete/{book_id}")
	public ResponseEntity<String> deleteBookById(@PathVariable Integer book_id) {
		try {
			Book book = bookService.getBookById(book_id);
			if (book != null) {				
				bookService.deleteBook(book_id);
				return new ResponseEntity<>("Libro borrado", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe Libro con ese ID", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
