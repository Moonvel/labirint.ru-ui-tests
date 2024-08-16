package ru.moonvel.labirint.utils.callbacks;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AllureExtension implements BeforeAllCallback {

	@Override
	public void beforeAll(ExtensionContext extensionContext) {
		SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
				.screenshots(true)
				.savePageSource(false));
	}
}
