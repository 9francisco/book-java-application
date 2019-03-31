package com.gdf.book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.gdf.book.model.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
     @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }

     private String getExternalRootUrl() {
         return "https://www.anapioficeandfire.com";
     }

     @Test
     public void contextLoads() {

     }

     @Test
     public void testGetAllExternalBooksByName() {
    	String bookName = "A Game of Thrones";
    	HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getExternalRootUrl() + "/api/v1/external-books?name=" + bookName,
        HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

     @Test
     public void testGetAllBooks() {
    	HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/books",
        HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetBookById() {
        Book book = restTemplate.getForObject(getRootUrl() + "/api/v1/books/1", Book.class);
        System.out.println(book.getName());
        assertNotNull(book);
    }

    @Test
    public void testCreateBook() {
        Book book = new Book();
        book.setName("My First Book");
        book.setIsbn("999-123456789");
        book.setAuthors(new String[] {"Marc Gav", "Gerald F."});
        ResponseEntity<Book> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/books", book, Book.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateBook() {
        int id = 1;
        Book book = restTemplate.getForObject(getRootUrl() + "/api/v1/books/" + id, Book.class);
        book.setName("My Second Edition Book");
        restTemplate.postForObject(getRootUrl() + "/api/v1/books/" + id + "?_method=patch",
        		book, Book.class);
        Book updatedEmployee = restTemplate.getForObject(getRootUrl() + "/api/v1/books/" + id, Book.class);
        assertNotNull(updatedEmployee);
    }

    @Test
    public void testDeleteBook() {
         int id = 1;
         Book book = restTemplate.getForObject(getRootUrl() + "/api/v1/books/" + id, Book.class);
         assertNotNull(book);
         restTemplate.delete(getRootUrl() + "/api/v1/books/" + id);
         try {
              book = restTemplate.getForObject(getRootUrl() + "/api/v1/books/" + id, Book.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }

}