package dev.rachamon.yanamibot;

import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.rachamon.yanamibot.api.abstracts.YanamiBotFileAbstract;
import dev.rachamon.yanamibot.api.services.YanamiBotCommandService;
import dev.rachamon.yanamibot.api.services.YanamiBotService;
import dev.rachamon.yanamibot.configs.EventsConfig;
import dev.rachamon.yanamibot.configs.LanguageConfig;
import dev.rachamon.yanamibot.configs.MainConfig;
import dev.rachamon.yanamibot.managers.BotManager;
import dev.rachamon.yanamibot.managers.YanamiBotPluginManager;
import dev.rachamon.yanamibot.utils.LoggerUtil;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.spongepowered.api.Game;
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

/**
 * The type Yanami bot.
 */
@Plugin(id = "yanamibot", name = "YanamiBot", description = "Simple Response Bot", authors = {"Rachamon"})
public class YanamiBot {

    private static YanamiBot instance;
    private static boolean isInitialized = false;

    private Components components;
    private YanamiBotFileAbstract<MainConfig> config;
    private YanamiBotFileAbstract<LanguageConfig> language;
    private YanamiBotFileAbstract<EventsConfig> events;
    private LoggerUtil logger;
    private YanamiBotPluginManager yanamiBotPluginManager;

    /**
     * The Factory.
     */
    @Inject
    public GuiceObjectMapperFactory factory;

    /**
     * The Sponge injector.
     */
    @Inject
    Injector spongeInjector;

    /**
     * The Game.
     */
    @Inject
    Game game;

    @Inject
    private Injector botInjector;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path directory;

    @Inject
    private PluginContainer container;

    /**
     * On pre initialize.
     *
     * @param event the event
     */
    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
        instance = this;
        this.setLogger(new LoggerUtil(Sponge.getServer()));
        this.setYanamiBotPluginManager(new YanamiBotPluginManager());

        this.getPluginManager().preInitialize();
        this.getLogger().info("On Pre Initialize YanamiBot...");
    }

    /**
     * On initialize.
     *
     * @param event the event
     */
    @Listener(order = Order.EARLY)
    public void onInitialize(GameInitializationEvent event) {
        getInstance().getLogger().info("On Initialize YanamiBot...");
        getInstance().getPluginManager().initialize();
    }

    /**
     * On start.
     *
     * @param event the event
     */
    @Listener
    public void onStart(GameStartedServerEvent event) {
        if (!this.isInitialized()) return;
        getInstance().getLogger().info("On Start YanamiBot...");
        getInstance().getPluginManager().start();
    }

    /**
     * On post initialize.
     *
     * @param event the event
     */
    @Listener
    public void onPostInitialize(GamePostInitializationEvent event) {
        getInstance().getLogger().info("On Post Initialize YanamiBot");
        getInstance().getPluginManager().postInitialize();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static YanamiBot getInstance() {
        return instance;
    }


    /**
     * Is initialized boolean.
     *
     * @return the boolean
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Sets is initialized.
     *
     * @param isInitialized the is initialized
     */
    public void setIsInitialized(boolean isInitialized) {
        YanamiBot.isInitialized = isInitialized;
    }

    /**
     * Gets logger.
     *
     * @return the logger
     */
    public LoggerUtil getLogger() {
        return logger;
    }

    /**
     * Sets logger.
     *
     * @param logger the logger
     */
    public void setLogger(LoggerUtil logger) {
        this.logger = logger;
    }

    /**
     * Gets sponge injector.
     *
     * @return the sponge injector
     */
    public Injector getSpongeInjector() {
        return spongeInjector;
    }

    /**
     * Gets directory.
     *
     * @return the directory
     */
    public Path getDirectory() {
        return directory;
    }

    /**
     * Gets container.
     *
     * @return the container
     */
    public PluginContainer getContainer() {
        return container;
    }

    /**
     * Gets bot injector.
     *
     * @return the bot injector
     */
    public Injector getBotInjector() {
        return botInjector;
    }

    /**
     * Sets bot injector.
     *
     * @param botInjector the bot injector
     */
    public void setBotInjector(Injector botInjector) {
        this.botInjector = botInjector;
    }

    /**
     * Gets components.
     *
     * @return the components
     */
    public Components getComponents() {
        return components;
    }

    /**
     * Sets components.
     *
     * @param components the components
     */
    public void setComponents(Components components) {
        this.components = components;
    }

    /**
     * Gets bot manager.
     *
     * @return the bot manager
     */
    public BotManager getBotManager() {
        return this.components.botManager;
    }

    /**
     * Gets config.
     *
     * @return the config
     */
    public MainConfig getConfig() {
        return config.getRoot();
    }

    /**
     * Sets config manager.
     *
     * @param config the config
     */
    public void setConfigManager(YanamiBotFileAbstract<MainConfig> config) {
        this.config = config;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public LanguageConfig getLanguage() {
        return language.getRoot();
    }

    /**
     * Sets language manager.
     *
     * @param language the language
     */
    public void setLanguageManager(YanamiBotFileAbstract<LanguageConfig> language) {
        this.language = language;
    }


    /**
     * Gets plugin manager.
     *
     * @return the plugin manager
     */
    public YanamiBotPluginManager getPluginManager() {
        return yanamiBotPluginManager;
    }

    /**
     * Sets yanami bot plugin manager.
     *
     * @param yanamiBotPluginManager the yanami bot plugin manager
     */
    public void setYanamiBotPluginManager(YanamiBotPluginManager yanamiBotPluginManager) {
        this.yanamiBotPluginManager = yanamiBotPluginManager;
    }

    /**
     * Gets events config.
     *
     * @return the events config
     */
    public EventsConfig getEventsConfig() {
        return events.getClazz();
    }

    /**
     * Sets events config.
     *
     * @param events the events
     */
    public void setEventsConfig(EventsConfig events) {
        this.events.setClazz(events);
    }

    /**
     * Sets main config.
     *
     * @param config the config
     */
    public void setMainConfig(MainConfig config) {
        this.config.setClazz(config);
    }

    /**
     * Sets language config.
     *
     * @param languageConfig the language config
     */
    public void setLanguageConfig(LanguageConfig languageConfig) {
        this.language.setClazz(languageConfig);
    }

    /**
     * Gets events manager.
     *
     * @return the events manager
     */
    public YanamiBotFileAbstract<EventsConfig> getEventsManager() {
        return events;
    }

    /**
     * Gets config manager.
     *
     * @return the config manager
     */
    public YanamiBotFileAbstract<MainConfig> getConfigManager() {
        return config;
    }

    /**
     * Gets language manager.
     *
     * @return the language manager
     */
    public YanamiBotFileAbstract<LanguageConfig> getLanguageManager() {
        return language;
    }

    /**
     * Sets events manager.
     *
     * @param events the events
     */
    public void setEventsManager(YanamiBotFileAbstract<EventsConfig> events) {
        this.events = events;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Gets command service.
     *
     * @return the command service
     */
    public YanamiBotCommandService getCommandService() {
        return YanamiBotCommandService.getInstance();
    }

    /**
     * Gets factory.
     *
     * @return the factory
     */
    public GuiceObjectMapperFactory getFactory() {
        return factory;
    }

    /**
     * The type Components.
     */
    public static class Components {
        @Inject
        private BotManager botManager;

        @Inject
        private YanamiBotService botService;
    }
}