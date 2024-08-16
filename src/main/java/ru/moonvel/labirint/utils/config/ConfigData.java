package ru.moonvel.labirint.utils.config;

import static ru.moonvel.labirint.utils.config.ConfigReader.getProperty;

public enum ConfigData {
    BASE_URL {
        public String getValue() {
            return getProperty("labirint.url");
        }
    },
    VK_LOGIN {
        public String getValue() {
            return getProperty("labirint.auth.vk.login");
        }
    },
    VK_PASSWORD {
        public String getValue() {
            return getProperty("labirint.auth.vk.password");
        }

        @Override
        public String toString() {
            return "********";
        }
    },

    CLEAR_CACHE_AND_COOKIES {
        public String getValue() {
            return getProperty("selenide.clearCacheAndCookies");
        }
    };


    public abstract String getValue();

    @Override
    public String toString() {
        return this.getValue();
    }
}
