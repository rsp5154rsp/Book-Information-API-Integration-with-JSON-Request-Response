package com.example.bookinfo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.bookinfo.model.BookResponse;
import com.example.bookinfo.model.ExternalBookResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookService {
	private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public BookResponse getBookDetails(String isbn) throws Exception {
        String url = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            if (responseBody != null && !responseBody.isEmpty()) {
                String key = "ISBN:" + isbn;
                ExternalBookResponse externalBookResponse = objectMapper.readValue(responseBody, ExternalBookResponse.class);
                return BookResponse.builder()
                        .bookIsbn(isbn)
                        .title(externalBookResponse.getTitle())
                        .publishers(externalBookResponse.getPublishers().stream().map(ExternalBookResponse.Publisher::getName).toList())
                        .authors(externalBookResponse.getAuthors().stream().map(a -> BookResponse.Author.builder().name(a.getName()).build()).toList())
                        .totalPages(externalBookResponse.getNumber_of_pages())
                        .publishedDate(externalBookResponse.getPublish_date())
                        .build();
            } else {
                throw new Exception("Book information not found");
            }
        } else {
            throw new Exception("Failed to fetch book details");
        }
    }
}
