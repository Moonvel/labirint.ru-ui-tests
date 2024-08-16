package ru.moonvel.labirint.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.moonvel.labirint.assertions.BookAssertions;
import ru.moonvel.labirint.dto.book.BookOnCart;
import ru.moonvel.labirint.dto.book.BookOnSearch;
import ru.moonvel.labirint.page.BookPreviewOnCartPage;
import ru.moonvel.labirint.page.BookPreviewOnSearchPage;
import ru.moonvel.labirint.page.CartPage;
import ru.moonvel.labirint.page.CookiesPage;
import ru.moonvel.labirint.page.HeaderPage;
import ru.moonvel.labirint.page.SearchResultsPage;
import ru.moonvel.labirint.test_data.BookDataFabric;
import ru.moonvel.labirint.utils.callbacks.AllureExtension;
import ru.moonvel.labirint.utils.callbacks.ConfigExtension;

import static ru.moonvel.labirint.utils.config.ConfigData.BASE_URL;
import static ru.moonvel.labirint.utils.config.ConfigData.CLEAR_CACHE_AND_COOKIES;

@ExtendWith({ConfigExtension.class, AllureExtension.class})
public class CartUITests {

	@BeforeEach
	public void beforeEach() {
		Selenide.open(BASE_URL.getValue());
		CookiesPage.of().clickCookiePolicyButton();
	}

	@AfterEach
	public void afterEach() {
		if (Boolean.parseBoolean(CLEAR_CACHE_AND_COOKIES.getValue()) && WebDriverRunner.hasWebDriverStarted()) {
			WebDriverRunner.clearBrowserCache();
			Selenide.clearBrowserCookies();
			Selenide.clearBrowserLocalStorage();
		}
	}

	@Test
	@Description("Во время теста происходит поиск книги 'Жизнь и удивительные приключения морехода Робинзона Крузо'," +
			"добавление книги с максимальной ценой в корзину, сравнение информации о книге на странице товара и на странице корзины")
	public void addBookToCartAndVerifyDetailsTest() {
		BookOnSearch searchedBook = BookDataFabric.getRobinsonCrusoeBookData();

		HeaderPage.of().fillSearchField(searchedBook.getTitle());
		SelenideElement rootBookElementOnSearchResultPage = SearchResultsPage.of().getRootBookElementWithMaxPrice();
		BookPreviewOnSearchPage bookPreviewOnSearchPage = new BookPreviewOnSearchPage(rootBookElementOnSearchResultPage);
		BookOnSearch bookOnSearch = bookPreviewOnSearchPage.parseBookOnSearchPage();
		bookPreviewOnSearchPage.clickAddToCart();
		HeaderPage.of().clickCartButton();
		SelenideElement rootBookElementOnCart = CartPage.of().getRootBookElement(bookOnSearch);
		BookPreviewOnCartPage bookPreviewOnCartPage = new BookPreviewOnCartPage(rootBookElementOnCart);
		BookOnCart bookOnCart = bookPreviewOnCartPage.parseBookOnCartPage();
		BookAssertions.assertBooks(bookOnSearch, bookOnCart);
	}

	@Test
	@Description("Во время теста происходит поиск книги 'SQL руководство', добавление в корзину самой дешевой, переход в корзину" +
			"нажатие кнопки 'Очистить корзину', выполнение проверки корректной очистки")
	public void clearCartTest() {
		BookOnSearch searchedBook = BookDataFabric.getSqlManualBookData();

		HeaderPage.of().fillSearchField(searchedBook.getTitle());
		SelenideElement rootBookElementOnSearchResultPage = SearchResultsPage.of().getRootBookElementWithMinPrice();
		BookPreviewOnSearchPage bookPreviewOnSearchPage = new BookPreviewOnSearchPage(rootBookElementOnSearchResultPage);
		bookPreviewOnSearchPage.clickAddToCart();
		HeaderPage.of().clickCartButton();
		CartPage cartPage = new CartPage();
		cartPage.clickClearCart();
		cartPage.checkCartAfterClear();
	}

}
