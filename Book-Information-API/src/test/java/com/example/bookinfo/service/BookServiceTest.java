package com.example.bookinfo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.bookinfo.model.BookResponse;
import com.example.bookinfo.model.ExternalBookResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetBookDetails() throws Exception {
		// Given
		String isbn = "1234567";
		String url = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";

		ExternalBookResponse externalBookResponse = new ExternalBookResponse();
		externalBookResponse.setTitle("lord of ring");

		when(restTemplate.getForEntity(url, String.class)).thenReturn(new ResponseEntity<>("{}", HttpStatus.OK));
		when(objectMapper.readValue(anyString(), ExternalBookResponse.class)).thenReturn(externalBookResponse);

		// When
		BookResponse bookResponse = bookService.getBookDetails(isbn);

		// Then
		assertEquals(isbn, bookResponse.getBookIsbn());
		assertEquals("lord of ring", bookResponse.getTitle());
	}

}
