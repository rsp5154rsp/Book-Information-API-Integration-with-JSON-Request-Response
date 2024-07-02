package com.example.bookinfo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalBookResponse {
	private String title;
    private List<Publisher> publishers;
    private List<Author> authors;
    private int number_of_pages;
    private String publish_date;

    public ExternalBookResponse(String title, List<Publisher> publishers, List<Author> authors, int number_of_pages,
			String publish_date) {
		super();
		this.title = title;
		this.publishers = publishers;
		this.authors = authors;
		this.number_of_pages = number_of_pages;
		this.publish_date = publish_date;
	}

	public ExternalBookResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Publisher {
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Author {
        private String name;
    }
}
