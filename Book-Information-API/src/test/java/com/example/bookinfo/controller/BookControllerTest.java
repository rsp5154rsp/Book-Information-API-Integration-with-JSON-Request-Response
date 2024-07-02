package com.example.bookinfo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.bookinfo.model.BookRequest;
import com.example.bookinfo.model.BookResponse;
import com.example.bookinfo.service.BookService;

class BookControllerTest {

	@InjectMocks
	private BookController bookController;

	@Mock
	private BookService bookService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetBookDetails() throws Exception {
		// Given
		BookRequest bookRequest = new BookRequest();
		bookRequest.setBookIsbn("1234567");

		BookResponse bookResponse = new BookResponse();
		bookResponse.setBookIsbn("1234567");
		bookResponse.setTitle("Ramayan");

		when(bookService.getBookDetails("1234567")).thenReturn(bookResponse);

		// When
		ResponseEntity<?> responseEntity = bookController.getBookDetails(bookRequest);

		// Then
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(bookResponse, responseEntity.getBody());
	}

}
