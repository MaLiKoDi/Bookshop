package com.BookShopSpring.bookshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookShopSpring.bookshop.models.Publisher;
import com.BookShopSpring.bookshop.repositories.IPublisherRepository;

@Service
public class PublisherServiceImpl implements IPublisherService {
	
	@Autowired
	private IPublisherRepository publisherRepository;

	@Override
	public void savePublisher(Publisher publisher) {
		publisherRepository.save(publisher);
		
	}

	@Override
	public List<Publisher> getAllPublisher() {
		List<Publisher> allPublisher = publisherRepository.findAll();
		return allPublisher;
	}

	@Override
	public Publisher getPublisherById(Integer id) {
		Publisher publisher = publisherRepository.findById(id).orElse(null);
		return publisher;
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		this.savePublisher(publisher);
		
	}

	@Override
	public void deletePublisher(Integer id) {
		publisherRepository.deleteById(id);
		
	}

}
