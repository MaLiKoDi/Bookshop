package com.BookShopSpring.bookshop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BookShopSpring.bookshop.models.Publisher;


@Service
public interface IPublisherService {
	
	public void savePublisher(Publisher publisher);
	
	public List<Publisher> getAllPublisher();
	
	public Publisher getPublisherById(Integer id);
	
	public void updatePublisher(Publisher client);
	
	public void deletePublisher(Integer id);

}
