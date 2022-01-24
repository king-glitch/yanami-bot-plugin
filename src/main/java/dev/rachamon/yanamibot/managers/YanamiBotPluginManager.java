package dev.rachamon.yanamibot.managers;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.YanamiBotModule;

public class YanamiBotPluginManager {
    private final YanamiBot plugin = YanamiBot.getInstance();


    public void initialize() {
        this.plugin.setGuildInjector(this.plugin.getSpongeInjector().createChildInjector(new YanamiBotModule()));
        this.plugin.getGuildInjector().injectMembers(this.plugin.getComponents());
        this.plugin.setIsInitialized(true);
    }

    public void preInitialize() {
    }

    public void start() {
    }

    public void postInitialize() {
    }

    public void reload() {
    }
}
