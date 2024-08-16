package ru.moonvel.labirint.utils.callbacks;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static ru.moonvel.labirint.utils.config.ConfigData.BROWSER;
import static ru.moonvel.labirint.utils.config.ConfigData.BROWSER_SIZE;
import static ru.moonvel.labirint.utils.config.ConfigReader.readProperties;

public class ConfigExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        readProperties();
        Configuration.browserSize = BROWSER_SIZE.getValue();
        Configuration.browser = BROWSER.getValue();
    }
}
