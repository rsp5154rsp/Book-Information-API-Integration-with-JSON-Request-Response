package com.example.bookinfo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRequest {
	private String bookIsbn;
	
	public BookRequest() {
		// TODO Auto-generated constructor stub
	}

	public BookRequest(String bookIsbn) {
		super();
		this.bookIsbn = bookIsbn;
	}

	

}
