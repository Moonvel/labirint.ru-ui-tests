package ru.moonvel.labirint.assertions;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import ru.moonvel.labirint.dto.book.Book;

import java.util.ArrayList;

public abstract class BookAssertions {

    @Step("Сравнение информации о книге на разных страницах. Идентичность автора, названия, издателя, серии и цены")
    public static <T extends Book, V extends Book> void assertBooks(T sourceBook, V targetBook) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(sourceBook.getAuthor()).isEqualTo(targetBook.getAuthor());
            softly.assertThat(sourceBook.getTitle()).contains(targetBook.getTitle());
            softly.assertThat(sourceBook.getPublisher()).isEqualTo(targetBook.getPublisher());
            softly.assertThat(sourceBook.getSeries()).isEqualTo(targetBook.getSeries());
            softly.assertThat(sourceBook.getPrice()).isEqualTo(targetBook.getPrice());
        });
    }

    @Step("Проверка книги - идентичность автора и названия")
    public static <T extends Book> void assertBookByAuthorAndTitle(T sourceBook, T searchedBook) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(sourceBook.getTitle()).isEqualTo(searchedBook.getTitle());
            softly.assertThat(sourceBook.getAuthor()).isEqualTo(searchedBook.getAuthor());
        });
    }

    @Step("Проверка книг - идентичность автора и минимальной цены")
    public static <T extends Book> void assertBooksByAuthorAndMinPrice(ArrayList<T> books, T searchedBook) {
        SoftAssertions softly = new SoftAssertions();
        books.forEach(book -> {
            softly.assertThat(book.getAuthor()).isEqualTo(searchedBook.getAuthor());
            softly.assertThat(book.getPrice() > searchedBook.getPrice() || book.getPrice() == - 1)
                    .as("Реальная цена книги - " + book.getPrice() + ", оказалась меньше заданной минимальной цены - " + searchedBook.getPrice())
                    .isTrue();
        });
        softly.assertAll();
    }
}
