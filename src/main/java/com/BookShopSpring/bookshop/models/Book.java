package com.BookShopSpring.bookshop.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Long isbn;
	private String title;
	private Integer year;
	private Integer copy;
	private Integer borrowedCopy;
	private Integer remainingCopy;
	private Boolean registered;
	
	@OneToOne
    @JoinColumn(name = "author_code", referencedColumnName = "id")
	private Author author;
	@OneToOne
    @JoinColumn(name = "publisher_code", referencedColumnName = "id")
	private Publisher publisher;
	
	
	

}
