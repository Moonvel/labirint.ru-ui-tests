package ru.moonvel.labirint.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.moonvel.labirint.dto.book.BookOnInfo;

import static com.codeborne.selenide.Selenide.$;


public class BookInfoPage {
    private final SelenideElement rootElement = $("#product");
    private final SelenideElement bookTitle = rootElement.$("#product-title h1");
    private final SelenideElement bookAuthor = rootElement.$("[data-event-label='author']");
    private final SelenideElement bookPublisher = rootElement.$(".publisher [href*=pubhouse]");
    private final SelenideElement bookSeries = rootElement.$(".series [href*=series]");
    private final SelenideElement bookPrice = rootElement.$("[class*=val-number]");


    public static BookInfoPage of() {
        return new BookInfoPage();
    }


    public Integer getBookPrice() {
        int price = - 1;
        if (bookPrice.exists() && bookPrice.isDisplayed()) {
            price = Integer.parseInt(bookPrice.text());
        }
        return price;
    }

    public String getBookTitle() {
        String title = bookTitle.text();
        if (title.contains(":")) {
            title = title.split(":")[1].trim();
        }
        return title;
    }

    @Step("Получена книга со страницы информации о книге")
    public BookOnInfo getBookOnInfo() {
        return BookOnInfo.builder()
                .author(bookAuthor.text())
                .title(getBookTitle())
                .publisher(bookPublisher.text())
                .series(bookSeries.text())
                .price(getBookPrice())
                .build();
    }
}
