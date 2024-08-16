package ru.moonvel.labirint.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import ru.moonvel.labirint.dto.book.BookOnSearch;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

@Getter
public class SearchResultsPage {
	private final ElementsCollection bookCards = $$(".product-card");
	private final ElementsCollection bookPrices = $$(".product-card__price-current");


	public static SearchResultsPage of() {
		return new SearchResultsPage();
	}


	@Step("Получены книги со страницы  результатов поиска поиска")
	public ArrayList<BookOnSearch> parseBooksOnSearchPage(ElementsCollection rootBookElementsOnSearchResultPage) {
		ArrayList<BookOnSearch> booksOnSearch = new ArrayList<>();
		rootBookElementsOnSearchResultPage.forEach(bookCard -> {
			BookPreviewOnSearchPage bookPreviewOnSearchPage = new BookPreviewOnSearchPage(bookCard);
			BookOnSearch bookOnSearch = bookPreviewOnSearchPage.parseBookOnSearchPage();
			booksOnSearch.add(bookOnSearch);
		});
		return booksOnSearch;
	}

	@Step("Получен корневой элемент книги на странице результатов поиска с максимальной ценой")
	public SelenideElement getRootBookElementWithMaxPrice() {
		int maxPriceIndex = 0;
		int maxPrice = 0;
		bookPrices.forEach(price -> price.shouldBe(visible));
		for (int i = 0; i < bookPrices.size(); i++) {
			int price = Integer.parseInt(bookPrices.get(i)
												   .text()
												   .replaceAll("\\D", ""));
			if (price > maxPrice) {
				maxPrice = price;
				maxPriceIndex = i;
			}
		}
		return bookCards.get(maxPriceIndex);
	}

	@Step("Получен корневой элемент книги на странице результатов поиска с минимальной ценой")
	public SelenideElement getRootBookElementWithMinPrice() {
		int minPriceIndex = 0;
		int minPrice = Integer.MAX_VALUE;
		bookPrices.forEach(price -> price.shouldBe(visible));
		for (int i = 0; i < bookPrices.size(); i++) {
			int price = Integer.parseInt(bookPrices.get(i)
												   .text()
												   .replaceAll("\\D", ""));
			if (price < minPrice) {
				minPrice = price;
				minPriceIndex = i;
			}
		}
		return bookCards.get(minPriceIndex);
	}
}

