package ru.moonvel.labirint.test_data;

import ru.moonvel.labirint.dto.book.BookOnSearch;

public abstract class BookDataFabric {

    public static BookOnSearch getSchildtTheCompleteReferenceBookData() {
        return BookOnSearch
                .builder()
                .title("Java Полное руководство")
                .author("Шилдт Герберт")
                .price(1000)
                .build();
    }

    public static BookOnSearch getEckelThinkingInJavaBookData() {
        return BookOnSearch
                .builder()
                .title("Философия Java")
                .author("Эккель Брюс")
                .build();
    }

    public static BookOnSearch getRobinsonCrusoeBookData() {
        return BookOnSearch
                .builder()
                .title("Жизнь и удивительные приключения морехода Робинзона Крузо")
                .build();
    }

    public static BookOnSearch getSqlManualBookData() {
        return BookOnSearch
                .builder()
                .title("SQL руководство")
                .build();
    }
}
