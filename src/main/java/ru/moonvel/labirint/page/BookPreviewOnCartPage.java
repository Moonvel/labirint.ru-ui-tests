package ru.moonvel.labirint.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.moonvel.labirint.dto.book.BookOnCart;


public class BookPreviewOnCartPage {
	private final SelenideElement rootElement;
	private final SelenideElement bookTitle;
	private final SelenideElement bookAuthor;
	private final SelenideElement bookPublisher;
	private final SelenideElement bookSeries;
	private final SelenideElement bookPrice;


	public BookPreviewOnCartPage(SelenideElement rootPreviewBook) {
		this.rootElement = rootPreviewBook.shouldBe(Condition.visible);
		this.bookTitle = rootElement.$(".product-title");
		this.bookAuthor = rootElement.$(".product-author");
		this.bookPublisher = rootElement.$("[href*=pubhous]");
		this.bookSeries = rootElement.$("[href*=series]");
		this.bookPrice = rootElement.$(".price-val span");
	}


	public Integer getBookPrice() {
		int price = -1;
		if (bookPrice.exists()) {
			price = Integer.parseInt(bookPrice.text().replaceAll(" ", ""));
		}
		return price;
	}

	@Step("Получена книга со страницы корзины")
	public BookOnCart parseBookOnCartPage() {
		return BookOnCart.builder().author(bookAuthor.text())
						 .title(bookTitle.text())
						 .publisher(bookPublisher.text())
						 .series(bookSeries.text())
						 .price(getBookPrice())
						 .build();
	}
}
