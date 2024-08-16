package ru.moonvel.labirint.utils.callbacks;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static ru.moonvel.labirint.utils.config.ConfigReader.readProperties;

public class ConfigExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        readProperties();
    }
}
