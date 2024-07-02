package com.example.bookinfo.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {

	private String bookIsbn;
	private String title;
	private List<String> publishers;
	private List<Author> authors;
	private int totalPages;
	private String publishedDate;

	@Data
	@Builder
	public static class Author {
		private String name;
	}

	public BookResponse() {
		// TODO Auto-generated constructor stub
	}

	public BookResponse(String bookIsbn, String title, List<String> publishers, List<Author> authors, int totalPages,
			String publishedDate) {
		super();
		this.bookIsbn = bookIsbn;
		this.title = title;
		this.publishers = publishers;
		this.authors = authors;
		this.totalPages = totalPages;
		this.publishedDate = publishedDate;
	}

}
