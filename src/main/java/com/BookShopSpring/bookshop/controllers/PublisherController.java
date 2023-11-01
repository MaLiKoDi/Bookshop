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
import com.BookShopSpring.bookshop.models.Publisher;
import com.BookShopSpring.bookshop.services.PublisherServiceImpl;

;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
	
	@Autowired
	private PublisherServiceImpl publisherService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createPublisher(@RequestBody Publisher publisher) {

		try {
			publisherService.savePublisher(publisher);
			return new ResponseEntity<>("Editorial creada", HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("No se pudo crear Editorial", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Publisher>> getAllpublishers() {
		List<Publisher> allPublisher = publisherService.getAllPublisher();

		if (!allPublisher.isEmpty()) {
			return new ResponseEntity<>(allPublisher, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Publisher> getPublisherById(@PathVariable("id") Integer publisher_id) {
		Publisher publisher = publisherService.getPublisherById(publisher_id);

		if (publisher != null) {
			return new ResponseEntity<>(publisher, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/update")
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisher) {
		try {
			publisherService.updatePublisher(publisher);
			Publisher publisherUpdated = publisherService.getPublisherById(publisher.getId());

			if (publisherUpdated != null) {
				return new ResponseEntity<>(publisherUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/register/{publisher_id}")
	public ResponseEntity<String> registerPublisher(@PathVariable Integer publisher_id) {
		try {
			Publisher publisher = publisherService.getPublisherById(publisher_id);
			if (publisher != null && publisher.getRegistered() == false) {
				publisher.setRegistered(true);
				publisherService.updatePublisher(publisher);
				return new ResponseEntity<>("Editorial dada de alta", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe editorial con ese ID o ya fue dada de alta", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping("/remove/{publisher_id}")
	public ResponseEntity<String> removePublisher(@PathVariable Integer publisher_id) {
		try {
			Publisher publisher = publisherService.getPublisherById(publisher_id);
			if (publisher != null && publisher.getRegistered() != false) {
				publisher.setRegistered(false);
				publisherService.updatePublisher(publisher);
				return new ResponseEntity<>("Editorial dada de baja", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe editorial con ese ID o ya fue dada de baja", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@DeleteMapping("/delete/{publisher_id}")
	public ResponseEntity<String> deletePublisherById(@PathVariable Integer publisher_id) {
		try {
			Publisher publisher = publisherService.getPublisherById(publisher_id);
			if (publisher != null) {
				publisherService.deletePublisher(publisher_id);
				return new ResponseEntity<>("Editorial borrada", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No existe Editorial con ese ID", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
