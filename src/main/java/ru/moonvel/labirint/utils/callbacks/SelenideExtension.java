package ru.moonvel.labirint.utils.callbacks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static ru.moonvel.labirint.utils.config.ConfigData.CLEAR_CACHE_AND_COOKIES;

public class SelenideExtension implements AfterEachCallback, BeforeEachCallback {

	@Override
	public void afterEach(ExtensionContext context) {
		if (Boolean.parseBoolean(CLEAR_CACHE_AND_COOKIES.getValue()) && WebDriverRunner.hasWebDriverStarted()) {
			WebDriverRunner.clearBrowserCache();
			Selenide.clearBrowserCookies();
			Selenide.clearBrowserLocalStorage();
		}
	}

	@Override
	public void beforeEach(ExtensionContext extensionContext) {
	}
}