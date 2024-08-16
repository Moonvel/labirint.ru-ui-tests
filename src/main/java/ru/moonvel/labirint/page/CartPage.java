package ru.moonvel.labirint.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import ru.moonvel.labirint.dto.book.BookOnSearch;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    private final ElementsCollection bookCards = $$(".product-padding-cart");
    private final ElementsCollection bookPrices = $$(".price-val");
	private final ElementsCollection bookTitles = $$(".product-title");
	private final SelenideElement clearCart = $("[onclick*=clearBasketTab]");
	private final SelenideElement restoreCart = $("[onclick*=restoreBasket]");
	private final SelenideElement cartIsEmptyMessage = $("[name] .g-alttext-head");


    public static CartPage of() {
        return new CartPage();
    }


	@Step("Корзина очищена")
	public void clickClearCart() {
		clearCart.click();
	}

    @Step("Получен корневой элемент книги добавленной в корзину")
    public SelenideElement getRootBookElement(BookOnSearch book) {
		bookCards.get(0).shouldBe(visible);

		for (int i = 0; i < bookCards.size(); i++) {
			int price = Integer.parseInt(bookPrices.get(i).text().replaceAll("\\D", ""));
			String title = bookTitles.get(i).text();
			if (price == book.getPrice() && title.equals(book.getTitle())) {
				return bookCards.get(i);
			}
		}
		throw new NoSuchElementException("Книга с таким названием и ценой не существует");
	}

	@Step("Проверка состояния корзины после очистки")
	public void checkCartAfterClear() {
		String expectedMessage = "Ваша корзина пуста. Почему?";
		restoreCart.shouldBe(interactable);
		cartIsEmptyMessage.should(text(expectedMessage));
	}

}
