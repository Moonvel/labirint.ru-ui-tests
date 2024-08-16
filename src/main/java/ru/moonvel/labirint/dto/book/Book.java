package ru.moonvel.labirint.dto.book;


import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class Book {
	private String title;
	private String author;
	private Integer price;
	private String publisher;
	private String series;
}
