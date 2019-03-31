package com.gdf.book.service;

import java.util.List;

import com.gdf.book.model.Book;

/**
 * BookService interface for Books.
 * @author Gerald F.
 *
 */
public interface BookService {

    public List<Book> getAllBooks();

    public Book getBook(Long bookId);

    public Book save(Book book);

    public Book updateBook(Long bookId, Book book);

    public Book deleteBook(Long bookId);

}