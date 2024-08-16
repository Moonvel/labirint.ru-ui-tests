package ru.moonvel.labirint.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
	private final SelenideElement rootElement = $(".new-auth");
	private final SelenideElement loginWithVkRuButton = rootElement.$("[title='ВКонтакте']");
	private final SelenideElement waysToLoginWithSocialMediaButton = rootElement.$("a.js-show-soc");


	public static LoginPage of() {
		return new LoginPage();
	}


	@Step("Выбран способ авторизации с помощью VK.com")
	public void clickLoginWithVkRu() {
		waysToLoginWithSocialMediaButton.click();
		loginWithVkRuButton.click();
	}
}
