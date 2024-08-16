package ru.moonvel.labirint.utils.callbacks;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ru.moonvel.labirint.page.CookiesPage;

import static ru.moonvel.labirint.utils.config.ConfigData.BASE_URL;

public class BeforeUITestSetUp implements BeforeEachCallback {
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		Selenide.open(BASE_URL.getValue());
		CookiesPage.of().clickCookiePolicyButton();
	}
}