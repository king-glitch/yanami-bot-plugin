package dev.rachamon.yanamibot.configs;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.api.abstracts.YanamiBotFileAbstract;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class LanguageConfig extends YanamiBotFileAbstract<LanguageConfig> {
    public LanguageConfig(String fileName) {
        super(YanamiBot.getInstance(), fileName);
    }

    @Setting(value = "general", comment = "General Settings")
    private final LanguageGeneralCategorySetting mainCategorySetting = new LanguageGeneralCategorySetting();

    public LanguageGeneralCategorySetting getMainCategorySetting() {
        return mainCategorySetting;
    }

    @ConfigSerializable
    public static class LanguageGeneralCategorySetting {
        @Setting(comment = "is logging", value = "is-debug")
        private final boolean isDebug = true;

        public boolean isDebug() {
            return isDebug;
        }
    }
}
