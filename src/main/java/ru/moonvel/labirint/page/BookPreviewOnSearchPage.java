package ru.moonvel.labirint.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.moonvel.labirint.dto.book.BookOnSearch;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class BookPreviewOnSearchPage {
	private final SelenideElement rootElement;
	private final SelenideElement bookTitle;
	private final SelenideElement bookAuthor;
	private final SelenideElement bookPublisher;
	private final SelenideElement bookSeries;
	private final SelenideElement bookPrice;
	private final SelenideElement addToCartButton;
	private final SelenideElement addToCartButtonToCartCondition;


	public BookPreviewOnSearchPage(SelenideElement rootPreviewBook) {
		this.rootElement = rootPreviewBook;
		this.bookTitle = rootElement.$(".product-card__name");
		this.bookAuthor = rootElement.$(".product-card__author");
		this.bookPublisher = rootElement.$("[href*=pubhouse]");
		this.bookSeries = rootElement.$(".product-card__info-series");
		this.bookPrice = rootElement.$(".product-card__price-current");
		this.addToCartButton = rootElement.$(".btn-tocart");
		this.addToCartButtonToCartCondition = rootElement.$(".btn-more");
	}


	public String getBookSeries() {
		return bookSeries.exists() ? bookSeries.text() : "Без серии";
	}

	public Integer getBookPrice() {
		int price = -1;
		if (bookPrice.exists()) {
			price = Integer.parseInt(bookPrice.text().replaceAll("\\D", ""));
		}
		return price;
	}

	@Step("Получена книга со страницы  результатов поиска поиска")
	public BookOnSearch parseBookOnSearchPage() {
		return BookOnSearch.builder().author(bookAuthor.text())
						   .title(bookTitle.text())
						   .publisher(bookPublisher.text())
						   .series(getBookSeries())
						   .price(getBookPrice())
						   .build();
	}

	@Step("Товар добавлен в корзину")
	public void clickAddToCart() {
		addToCartButton.click();
		addToCartButtonToCartCondition.shouldBe(visible, Duration.ofSeconds(5L));
	}

	@Step("Переход в карточку книги")
	public void clickBookTitle() {
		bookTitle.click();
	}
}


