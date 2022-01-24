package dev.rachamon.yanamibot;

import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.rachamon.yanamibot.api.services.YanamiBotService;
import dev.rachamon.yanamibot.configs.LanguageConfig;
import dev.rachamon.yanamibot.configs.MainConfig;
import dev.rachamon.yanamibot.managers.BotManager;
import dev.rachamon.yanamibot.managers.YanamiBotPluginManager;
import dev.rachamon.yanamibot.utils.LoggerUtil;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;

@Plugin(id = "yanamibot", name = "YanamiBot", description = "Simple Response Bot", authors = {"Rachamon"})
public class YanamiBot {

    private static YanamiBot instance;
    private static boolean isInitialized = false;

    private Components components;
    private MainConfig config;
    private LanguageConfig language;
    private LoggerUtil logger;
    private YanamiBotPluginManager yanamiBotPluginManager;

    @Inject
    Injector spongeInjector;

    @Inject
    private GuiceObjectMapperFactory factory;

    @Inject
    private Injector botInjector;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path directory;

    @Inject
    private PluginContainer container;

    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
        instance = this;
        this.setLogger(new LoggerUtil(Sponge.getServer()));
        this.setYanamiBotPluginManager(new YanamiBotPluginManager());

        this.getPluginManager().preInitialize();
        this.getLogger().info("On Pre Initialize YanamiBot...");
    }

    @Listener(order = Order.EARLY)
    public void onInitialize(GameInitializationEvent event) {
        getInstance().getLogger().info("On Initialize YanamiBot...");
        getInstance().getPluginManager().initialize();
    }

    @Listener
    public void onStart(GameStartedServerEvent event) {
        if (!this.isInitialized()) return;
        getInstance().getLogger().info("On Start YanamiBot...");
        getInstance().getPluginManager().start();
        this.getLogger().info(String.valueOf(getInstance().getConfig().getMainCategorySetting().isDebug()));
        this.getLogger().info(String.valueOf(getInstance().getLanguage().getMainCategorySetting().isDebug()));
    }

    @Listener
    public void onPostInitialize(GamePostInitializationEvent event) {
        getInstance().getLogger().info("On Post Initialize YanamiBot");
        getInstance().getPluginManager().postInitialize();
    }

    public static YanamiBot getInstance() {
        return instance;
    }


    public boolean isInitialized() {
        return isInitialized;
    }

    public void setIsInitialized(boolean isInitialized) {
        YanamiBot.isInitialized = isInitialized;
    }

    public LoggerUtil getLogger() {
        return logger;
    }

    public void setLogger(LoggerUtil logger) {
        this.logger = logger;
    }

    public Injector getSpongeInjector() {
        return spongeInjector;
    }

    public Path getDirectory() {
        return directory;
    }

    public PluginContainer getContainer() {
        return container;
    }

    public GuiceObjectMapperFactory getFactory() {
        return factory;
    }

    public void setFactory(GuiceObjectMapperFactory factory) {
        this.factory = factory;
    }

    public Injector getBotInjector() {
        return botInjector;
    }

    public void setBotInjector(Injector botInjector) {
        this.botInjector = botInjector;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public BotManager getBotManager() {
        return this.components.botManager;
    }

    public YanamiBotService getBotService() {
        return this.components.botService;
    }

    public MainConfig getConfig() {
        return config;
    }

    public void setConfig(MainConfig config) {
        this.config = config;
    }

    public LanguageConfig getLanguage() {
        return language;
    }

    public void setLanguage(LanguageConfig language) {
        this.language = language;
    }


    public YanamiBotPluginManager getPluginManager() {
        return yanamiBotPluginManager;
    }

    public void setYanamiBotPluginManager(YanamiBotPluginManager yanamiBotPluginManager) {
        this.yanamiBotPluginManager = yanamiBotPluginManager;
    }

    public static class Components {
        @Inject
        private BotManager botManager;

        @Inject
        private YanamiBotService botService;
    }
}