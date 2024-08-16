package ru.moonvel.labirint.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.moonvel.labirint.assertions.BookAssertions;
import ru.moonvel.labirint.dto.book.BookOnInfo;
import ru.moonvel.labirint.dto.book.BookOnSearch;
import ru.moonvel.labirint.page.BookInfoPage;
import ru.moonvel.labirint.page.BookPreviewOnSearchPage;
import ru.moonvel.labirint.page.HeaderPage;
import ru.moonvel.labirint.page.LoginPage;
import ru.moonvel.labirint.page.SearchResultsPage;
import ru.moonvel.labirint.page.VkPage;
import ru.moonvel.labirint.test_data.BookDataFabric;
import ru.moonvel.labirint.utils.callbacks.AfterUITestClear;
import ru.moonvel.labirint.utils.callbacks.AllureExtension;
import ru.moonvel.labirint.utils.callbacks.BeforeUITestSetUp;
import ru.moonvel.labirint.utils.callbacks.ConfigExtension;

import java.util.ArrayList;

import static ru.moonvel.labirint.utils.config.ConfigData.VK_LOGIN;
import static ru.moonvel.labirint.utils.config.ConfigData.VK_PASSWORD;

@ExtendWith({ConfigExtension.class, AllureExtension.class, BeforeUITestSetUp.class, AfterUITestClear.class})
public class BookSearchUITests {

    @Test
    @Description("Тест проверяет, что при поиске книги 'Java Полное руководство' находятся только "
            + "книги автора 'Шилдт Герберт' со стоимостью выше 1000 руб. или книга отсутствует в продаже")
    public void bookSearchAndVerifyAuthorAndPriceTest() {
        BookOnSearch searchedBook = BookDataFabric.getSchildtTheCompleteReferenceBookData();

        HeaderPage.of().clickMyCabinetButton();
        LoginPage.of().clickLoginWithVkRu();
        VkPage.of().fillUserAndPasswordSubmitLogin(VK_LOGIN, VK_PASSWORD);
        HeaderPage.of().fillSearchField(searchedBook.getTitle());
        ElementsCollection bookCards = SearchResultsPage.of().getBookCards();
        ArrayList<BookOnSearch> booksOnSearch = SearchResultsPage.of().parseBooksOnSearchPage(bookCards);
        BookAssertions.assertBooksByAuthorAndMinPrice(booksOnSearch, searchedBook);
    }

    @Test
    @Description("Тест проверяет, что первый найденный продукт по запросу 'Философия Java' написан автором 'Эккель Брюс'" +
            " и имеет название 'Философия Java'. Далее происходит переход в карточку книги, проверяется заголовок, автор и название")
    public void bookSearchAndBookInfoDetailsTest() {
        BookOnSearch searchedBook = BookDataFabric.getEckelThinkingInJavaBookData();

        HeaderPage.of().fillSearchField(searchedBook.getTitle());
        SelenideElement rootBookElement = SearchResultsPage.of().getBookCards().first();
        BookPreviewOnSearchPage bookPreviewOnSearchPage = new BookPreviewOnSearchPage(rootBookElement);
        BookOnSearch bookOnSearch = bookPreviewOnSearchPage.parseBookOnSearchPage();
        BookAssertions.assertBookByAuthorAndTitle(bookOnSearch, searchedBook);
        bookPreviewOnSearchPage.clickBookTitle();
        BookOnInfo bookOnInfo = BookInfoPage.of().getBookOnInfo();
        BookAssertions.assertBooks(bookOnSearch, bookOnInfo);
    }
}
