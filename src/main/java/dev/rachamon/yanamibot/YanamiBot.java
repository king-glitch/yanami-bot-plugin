package dev.rachamon.yanamibot;

import com.google.inject.Inject;
import com.google.inject.Injector;
import ninja.leaping.configurate.objectmapping.GuiceObjectMapperFactory;
import org.slf4j.Logger;
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

    @Inject
    private Logger logger;

    @Inject
    Injector spongeInjector;

    @Inject
    private GuiceObjectMapperFactory factory;

    @Inject
    private Injector guildInjector;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path directory;

    @Inject
    private PluginContainer container;

    @Listener
    public void onPreInitialize(GamePreInitializationEvent event) {
    }

    @Listener(order = Order.EARLY)
    public void onInitialize(GameInitializationEvent event) {
    }

    @Listener
    public void onStart(GameStartedServerEvent event) {
    }

    @Listener
    public void onPostInitialize(GamePostInitializationEvent event) {
    }

    public static YanamiBot getInstance() {
        return instance;
    }

    public static void setInstance(YanamiBot instance) {
        YanamiBot.instance = instance;
    }

    public boolean isIsInitialized() {
        return isInitialized;
    }

    public void setIsInitialized(boolean isInitialized) {
        YanamiBot.isInitialized = isInitialized;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Injector getSpongeInjector() {
        return spongeInjector;
    }

    public void setSpongeInjector(Injector spongeInjector) {
        this.spongeInjector = spongeInjector;
    }

    public Path getDirectory() {
        return directory;
    }

    public void setDirectory(Path directory) {
        this.directory = directory;
    }

    public PluginContainer getContainer() {
        return container;
    }

    public void setContainer(PluginContainer container) {
        this.container = container;
    }

    public GuiceObjectMapperFactory getFactory() {
        return factory;
    }

    public void setFactory(GuiceObjectMapperFactory factory) {
        this.factory = factory;
    }

    public Injector getGuildInjector() {
        return guildInjector;
    }

    public void setGuildInjector(Injector guildInjector) {
        this.guildInjector = guildInjector;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public static class Components {

    }
}