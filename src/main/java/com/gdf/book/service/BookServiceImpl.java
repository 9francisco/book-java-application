package com.gdf.book.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdf.book.exception.BookNotFoundException;
import com.gdf.book.model.Book;
import com.gdf.book.repository.BookRepository;

/**
 * BookServiceImpl for Books.
 * @author Gerald F.
 *
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
	public List<Book> getAllBooks() {
    	List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
	public Book getBook(Long bookId) {
	    Optional<Book> book = bookRepository.findById(bookId);
	    return book.orElse(null);
    }

    @Override
	public Book save(Book book) {
    	return bookRepository.save(book);
    }

    @Override
	public Book updateBook(Long bookId, Book book) {
    	Book existing = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book", "id", bookId));

    	if (null != book.getName()) {
	        existing.setName(book.getName());
	    }
	    if (null != book.getIsbn()) {
	        existing.setIsbn(book.getIsbn());
	    }
	    if (null != book.getAuthors()) {
	        existing.setAuthors(book.getAuthors());
	    }


	    if (book.getNumber_of_pages() != 0) {
	        existing.setNumber_of_pages(book.getNumber_of_pages());
	    }

	    if (null != book.getPublisher()) {
	        existing.setPublisher(book.getPublisher());
	    }

	    if (null != book.getCountry()) {
	        existing.setCountry(book.getCountry());
	    }

	    if (null != book.getReleasedate()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	        try {
				existing.setRelease_date(format.parse(book.getRelease_date()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    }
	    return bookRepository.save(existing);
    }

    @Override
	public Book deleteBook(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book", "id", bookId));
		bookRepository.delete(book);
		return book;
    }
}