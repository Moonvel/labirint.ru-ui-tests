package ru.moonvel.labirint.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CookiesPage {
    private final SelenideElement rootElement = $(".cookie-policy");
    private final SelenideElement cookiePolicyButton = rootElement.$(".cookie-policy__button");


    public static CookiesPage of() {
        return new CookiesPage();
    }


    @Step("Скрытие сообщения о cookie policy")
    public void clickCookiePolicyButton() {
        if (cookiePolicyButton.isDisplayed()) {
            cookiePolicyButton.click();
        }
    }
}
