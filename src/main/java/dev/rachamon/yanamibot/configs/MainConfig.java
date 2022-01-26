package dev.rachamon.yanamibot.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MainConfig {

    @Setting(value = "general", comment = "General Settings")
    private final GeneralCategorySetting mainCategorySetting = new GeneralCategorySetting();

    public GeneralCategorySetting getMainCategorySetting() {
        return mainCategorySetting;
    }

    @ConfigSerializable
    public static class GeneralCategorySetting {
        @Setting(comment = "is logging", value = "is-debug")
        private final boolean isDebug = true;

        public boolean isDebug() {
            return isDebug;
        }
    }
}
