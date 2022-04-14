package dev.rachamon.yanamibot.managers;

import dev.rachamon.api.sponge.config.SpongeAPIConfigFactory;
import dev.rachamon.api.sponge.exception.AnnotatedCommandException;
import dev.rachamon.api.sponge.implement.plugin.IRachamonPluginManager;
import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.YanamiBotModule;
import dev.rachamon.yanamibot.commands.YanamiBotMainCommand;
import dev.rachamon.yanamibot.configs.EventsConfig;
import dev.rachamon.yanamibot.configs.LanguageConfig;
import dev.rachamon.yanamibot.configs.MainConfig;
import dev.rachamon.yanamibot.listeners.ChatEventListener;

import java.util.List;

/**
 * The type Yanami bot plugin manager.
 */
public class YanamiBotPluginManager implements IRachamonPluginManager {
    private final YanamiBot plugin = YanamiBot.getInstance();


    /**
     * Initialize.
     */
    public void initialize() {
        this.plugin.setComponents(new YanamiBot.Components());
        this.plugin.setPluginInjector(this.plugin.getSpongeInjector().createChildInjector(new YanamiBotModule()));
        this.plugin.getPluginInjector().injectMembers(this.plugin.getComponents());
        this.plugin.setIsInitialized(true);
    }

    /**
     * Pre initialize.
     */
    public void preInitialize() {
    }

    /**
     * Start.
     */
    public void start() {
        this.plugin.getLogger().debug("Initializing events...");
    }

    /**
     * Post initialize.
     */
    public void postInitialize() {
        this.plugin.getLogger().debug("Initializing Configs...");
        this.configureConfigs();
        this.registerCommands();

        this.plugin.getGame().getEventManager().registerListeners(plugin, new ChatEventListener());

    }

    /**
     * Register commands.
     */
    public void registerCommands() {
        try {
            this.plugin.getCommandService().register(new YanamiBotMainCommand(), this.plugin);
            this.plugin.getLogger().debug("Initialized Commands");
        } catch (AnnotatedCommandException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reload.
     */
    public void reload() {
        this.plugin.getLogger().debug("Reloading Yanami Bot...");
        this.configureConfigs();
        this.registerCommands();

        this.plugin.getLogger().debug("Yanami Bot reloaded");
    }

    private void configureConfigs() {
        SpongeAPIConfigFactory<YanamiBot, MainConfig> config = new SpongeAPIConfigFactory<>(this.plugin, "main.conf");
        SpongeAPIConfigFactory<YanamiBot, LanguageConfig> language = new SpongeAPIConfigFactory<>(this.plugin, "language.conf");
        SpongeAPIConfigFactory<YanamiBot, EventsConfig> events = new SpongeAPIConfigFactory<>(this.plugin, "events.conf");

        this.plugin.setEventsManager(events);
        this.plugin.setConfigManager(config);
        this.plugin.setLanguageManager(language);


        this.plugin.setMainConfig(config
                .setHeader("Main Config")
                .setClazz(new MainConfig())
                .setClazzType(MainConfig.class)
                .build());

        this.plugin.setLanguageConfig(language
                .setHeader("Language Config")
                .setClazz(new LanguageConfig())
                .setClazzType(LanguageConfig.class)
                .build());

        this.plugin.setEventsConfig(events
                .setHeader("Bot Response Events Config")
                .setClazz(new EventsConfig())
                .setClazzType(EventsConfig.class)
                .build());

        this.plugin
                .getEventsManager()
                .getConfigRoot()
                .getNode("chat-responses")
                .getChildrenMap()
                .forEach((key, value) -> {
                    String permission = (String) value.getNode("permission").getValue();
                    List<String> regexes = (List<String>) value.getNode("regexes").getValue();
                    List<String> responses = (List<String>) value.getNode("responses").getValue();
                    List<String> commands = (List<String>) value.getNode("commands").getValue();

                    this.plugin
                            .getEventsConfig()
                            .getChatResponses()
                            .put((String) key, new EventsConfig.ChatResponse(permission, regexes, responses, commands));
                });

        this.plugin.getLogger().setDebug(this.plugin.getConfig().getMainCategorySetting().isDebug());
    }
}
