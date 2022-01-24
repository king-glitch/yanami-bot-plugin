package dev.rachamon.yanamibot.managers;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.YanamiBotModule;
import dev.rachamon.yanamibot.configs.LanguageConfig;
import dev.rachamon.yanamibot.configs.MainConfig;

public class YanamiBotPluginManager {
    private final YanamiBot plugin = YanamiBot.getInstance();


    public void initialize() {
        this.plugin.setComponents(new YanamiBot.Components());
        this.plugin.setBotInjector(this.plugin.getSpongeInjector().createChildInjector(new YanamiBotModule()));
        this.plugin.getBotInjector().injectMembers(this.plugin.getComponents());
        this.plugin.setIsInitialized(true);
    }

    public void preInitialize() {
    }

    public void start() {
        this.plugin.getLogger().debug("Initializing Databases...");
    }

    public void postInitialize() {
        this.plugin.getLogger().debug("Initializing Configs...");

        MainConfig config = new MainConfig("main.conf");
        LanguageConfig language = new LanguageConfig("language.conf");

        this.plugin.setConfig(
                config
                        .setHeader("Main Config")
                        .setClazz(config)
                        .setClazzType(MainConfig.class)
                        .build()
        );
        this.plugin.setLanguage(
                language
                        .setHeader("Language Config")
                        .setClazz(language)
                        .setClazzType(LanguageConfig.class)
                        .build()
        );
    }

    public void reload() {
    }
}
