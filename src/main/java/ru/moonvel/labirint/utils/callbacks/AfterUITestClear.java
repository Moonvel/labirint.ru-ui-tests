package ru.moonvel.labirint.utils.callbacks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static ru.moonvel.labirint.utils.config.ConfigData.CLEAR_CACHE_AND_COOKIES;

public class AfterUITestClear implements AfterEachCallback {

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		if (Boolean.parseBoolean(CLEAR_CACHE_AND_COOKIES.getValue()) && WebDriverRunner.hasWebDriverStarted()) {
			WebDriverRunner.clearBrowserCache();
			Selenide.clearBrowserCookies();
			Selenide.clearBrowserLocalStorage();
		}
	}
}