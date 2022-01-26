package dev.rachamon.yanamibot.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class LanguageConfig {

    @Setting(value = "general", comment = "General Settings")
    private final LanguageGeneralCategory generalCategory = new LanguageGeneralCategory();

    public LanguageGeneralCategory getGeneralCategory() {
        return generalCategory;
    }

    @ConfigSerializable
    public static class LanguageGeneralCategory {
        @Setting(comment = "plugin prefix", value = "prefix")
        private final String prefix = "&8[&d&l&oYanami&8] &f";

        @Setting(comment = "The bot name", value = "bot-name")
        private final String botName = "&d&l&oYanami";

        @Setting(comment = "message builder\n" +
                "variables : \n" +
                " - {bot-name} : the bot name\n" +
                " - {message} : bot response message\n" +
                " - {target} : target to response to", value = "message-builder")
        private final String messageBuilder = "&8[&4&lBOT&8] &8[{bot-name}&8] &a&l@{target}&f, &f{message}";

        public String getBotName() {
            return botName;
        }

        public String getMessageBuilder() {
            return messageBuilder;
        }

        public String getPrefix() {
            return prefix;
        }
    }
}
