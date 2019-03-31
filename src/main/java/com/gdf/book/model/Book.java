package com.gdf.book.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;
import com.gdf.book.util.BookResponseView;

/**
 * @author Gerald F.
 *
 */
@Entity(name = "Book")
public class Book implements Serializable {

    public Book(String name, String isbn, String[] authors, int number_of_pages, String publisher,
    		String country, Date release_date) {
        this.name = name;
        this.isbn = isbn;
        this.authors = authors;
        this.number_of_pages = number_of_pages;
        this.publisher = publisher;
        this.country = country;
        this.release_date = release_date;
    }

	public Book() {
	}

	@Id
   	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(BookResponseView.Response.class)
   	private Long id;

	@JsonView(BookResponseView.Response.class)
   	private String name;

	@JsonView(BookResponseView.Response.class)
   	private String isbn;

	@JsonView(BookResponseView.Response.class)
   	private String[] authors;

	@JsonView(BookResponseView.Response.class)
	private int number_of_pages;

	@JsonView(BookResponseView.Response.class)
	private String publisher;

	@JsonView(BookResponseView.Response.class)
	private String country;

	@JsonView(BookResponseView.Response.class)
	private Date release_date;

   	public Long getId() {
   		return id;
   	}

   	public void setId(Long id) {
   		this.id = id;
   	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getNumber_of_pages() {
		return number_of_pages;
	}

	public void setNumber_of_pages(int number_of_pages) {
		this.number_of_pages = number_of_pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public Date getReleasedate() {
		return release_date;
	}
	public String getRelease_date() {
		return (release_date != null) ? formatDate(release_date) : "";
	}

	public String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format.format(date);
		return formatted;
	}
}
