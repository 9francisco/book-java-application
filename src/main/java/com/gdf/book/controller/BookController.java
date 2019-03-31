package com.gdf.book.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonView;
import com.gdf.book.model.Book;
import com.gdf.book.model.IceAndFireBooks;
import com.gdf.book.repository.BookRepository;
import com.gdf.book.service.BookService;
import com.gdf.book.util.BookResponse;
import com.gdf.book.util.BookResponseView;

/**
 * BookController for Book APIs.
 * @author Gerald F.
 *
 */
@RestController
@RequestMapping("/api/v1")
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final String externalEndpoint = "https://www.anapioficeandfire.com/api/books?name=";

	@Autowired
	BookRepository bookRepository;

	@Autowired
	private BookService bookservice;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// -------------------Retrieve All External Books (Ice And Fire)---------------------------------------------
	@JsonView(BookResponseView.Response.class)
	@GetMapping("/external-books")
    public BookResponse getAllExternalBooksByName(@RequestParam("name") String nameOfABook) {
		logger.info("Retrieve All External Books with name: " + nameOfABook);
		String uri = externalEndpoint + nameOfABook;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<List<IceAndFireBooks>> response = restTemplate.exchange(uri,
        		HttpMethod.GET, entity, new ParameterizedTypeReference<List<IceAndFireBooks>>() {});
        List<IceAndFireBooks> data = response.getBody();

        return new BookResponse(HttpStatus.OK.value(), "success", null, data);
    }

	// -------------------Create a Book-------------------------------------------
	@JsonView(BookResponseView.Response.class)
	@PostMapping("/books")
    public BookResponse createBook(@RequestBody Book book) {
    	logger.info("Create Book: {}", book);

    	Book response = bookservice.save(book);

	    return new BookResponse(HttpStatus.CREATED.value(), "success", null, response);
    }

	// -------------------Retrieve All Books---------------------------------------------
	@JsonView(BookResponseView.Response.class)
	@GetMapping("/books")
	public BookResponse getAllBooks() {
		logger.info("Read All Books");
	    List<Book> response = bookservice.getAllBooks();
	    return new BookResponse(HttpStatus.OK.value(), "success", null, response);
	}

	// ------------------- Update a Book------------------------------------------------
	@JsonView(BookResponseView.ResponseWithMessage.class)
	@PatchMapping("/books/{id}")
	public BookResponse updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody Book book) {
		logger.info("Update Book with bookId: {}", bookId);
		Book response = bookservice.updateBook(bookId, book);

	    return new BookResponse(HttpStatus.OK.value(), "success",
	    		"The book " + response.getName() + " was updated successfully", response);
	}

	// ------------------- Delete a Book-----------------------------------------
	@JsonView(BookResponseView.ResponseWithMessage.class)
	@DeleteMapping("/books/{id}")
	public BookResponse deleteBook(@PathVariable(value = "id") Long bookId) {
		logger.info("Delete Book with bookId: {}", bookId);
		Book res = bookservice.deleteBook(bookId);
		Book response = bookservice.getBook(res.getId());

	    return new BookResponse(HttpStatus.NO_CONTENT.value(), "success",
	    		"The book " + res.getName() + " was deleted successfully", response);
	}

	// -------------------Retrieve Single Book------------------------------------------
	@JsonView(BookResponseView.Response.class)
	@GetMapping("/books/{id}")
	public BookResponse getBookById(@PathVariable(value = "id") Long bookId) {
		logger.info("Show Book with bookId: {}", bookId);
		Book response = bookservice.getBook(bookId);

		return new BookResponse(HttpStatus.OK.value(), "success", null, response);
	}
}
