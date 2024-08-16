package ru.moonvel.labirint.page;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.moonvel.labirint.utils.config.ConfigData;

import static com.codeborne.selenide.Selenide.$;

public class VkPage {
    private static final SelenideElement userNameField = $(Selectors.byName(("email")));
    private static final SelenideElement passwordField = $(Selectors.byName("pass"));
    private static final SelenideElement submitLoginButton = $("[type='submit']");


    public static VkPage of() {
        return new VkPage();
    }


    @Step("Авторизация на сайте с помощью VK.com")
    public void fillUserAndPasswordSubmitLogin(ConfigData userName, ConfigData passWord) {
        userNameField.sendKeys(userName.getValue());
        passwordField.sendKeys(passWord.getValue());
        submitLoginButton.click();
    }
}
