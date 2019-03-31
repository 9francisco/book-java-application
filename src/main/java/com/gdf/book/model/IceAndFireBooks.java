package com.gdf.book.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.gdf.book.util.BookResponseView;

/**
 * @author Gerald F.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IceAndFireBooks {
	@JsonView(BookResponseView.Response.class)
	private String name;

	@JsonView(BookResponseView.Response.class)
	private String isbn;

	@JsonView(BookResponseView.Response.class)
	private String[] authors;

	private int numberOfPages;

	@JsonView(BookResponseView.Response.class)
	private int number_of_pages;

	@JsonView(BookResponseView.Response.class)
	private String publisher;

	@JsonView(BookResponseView.Response.class)
	private String country;

	private Date released;

	@JsonView(BookResponseView.Response.class)
	private String release_date;

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

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public int getNumber_of_pages() {
		return numberOfPages;
	}

	public String getRelease_date() {
		return formatDate(released);
	}

	public String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format.format(date);
		return formatted;
	}

}
