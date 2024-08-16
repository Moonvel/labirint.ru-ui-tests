package ru.moonvel.labirint.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class HeaderPage {
    private final SelenideElement rootElement = $(".top-header");
    private final SelenideElement searchField = rootElement.$("input#search-field");
    private final SelenideElement cartButton = rootElement.$("li a[href*=cart]");
    private final SelenideElement mainCabinetButton = rootElement.$(".top-link-main_cabinet");


    public static HeaderPage of() {
        return new HeaderPage();
    }


    @Step("Поиск по запросу {query}")
    public void fillSearchField(String query) {
        searchField.sendKeys(query);
        searchField.pressEnter();
    }

    @Step("Осуществлен переход в корзину")
    public void clickCartButton() {
        cartButton.click();
    }

    @Step("Переход в личный кабинет")
    public void clickMyCabinetButton() {
        mainCabinetButton.click();
    }
}
