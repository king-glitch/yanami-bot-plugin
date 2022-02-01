package dev.rachamon.yanamibot;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.rachamon.yanamibot.api.services.YanamiBotService;
import dev.rachamon.yanamibot.managers.BotManager;

/**
 * The type Yanami bot module.
 */
public class YanamiBotModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BotManager.class).in(Scopes.SINGLETON);
        bind(YanamiBotService.class).in(Scopes.SINGLETON);
    }
}
