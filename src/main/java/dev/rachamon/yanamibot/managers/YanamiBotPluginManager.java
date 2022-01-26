package dev.rachamon.yanamibot.managers;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.YanamiBotModule;
import dev.rachamon.yanamibot.api.abstracts.YanamiBotFileAbstract;
import dev.rachamon.yanamibot.api.exceptions.AnnotatedCommandException;
import dev.rachamon.yanamibot.commands.YanamiBotMainCommand;
import dev.rachamon.yanamibot.configs.EventsConfig;
import dev.rachamon.yanamibot.configs.LanguageConfig;
import dev.rachamon.yanamibot.configs.MainConfig;
import dev.rachamon.yanamibot.listeners.ChatEventListener;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;

import java.util.List;

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
        this.plugin.getLogger().debug("Initializing events...");
    }

    public void postInitialize() {
        this.plugin.getLogger().debug("Initializing Configs...");

        YanamiBotFileAbstract<MainConfig> config = new YanamiBotFileAbstract<>(this.plugin, "main.conf");
        YanamiBotFileAbstract<LanguageConfig> language = new YanamiBotFileAbstract<>(this.plugin, "language.conf");
        YanamiBotFileAbstract<EventsConfig> events = new YanamiBotFileAbstract<>(this.plugin, "events.conf");

        this.plugin.setConfig(
                config
                        .setHeader("Main Config")
                        .setClazz(new MainConfig())
                        .setClazzType(MainConfig.class)
                        .build()
        );

        this.plugin.setLanguage(
                language
                        .setHeader("Language Config")
                        .setClazz(new LanguageConfig())
                        .setClazzType(LanguageConfig.class)
                        .build()
        );

        this.plugin.setEventsManager(events);
        this.plugin.setEventsConfig(
                events
                        .setHeader("Bot Response Events Config")
                        .setClazz(new EventsConfig())
                        .setClazzType(EventsConfig.class)
                        .build()
        );

        this.plugin.getEventsManager().getConfigRoot().getNode("chat-responses").getChildrenMap().forEach((key, value) -> {
            this.plugin.getLogger().debug((String) key);
            String permission = (String) value.getNode("permission").getValue();
            List<String> regexes = (List<String>) value.getNode("regexes").getValue();
            List<String> responses = (List<String>) value.getNode("responses").getValue();
            List<String> commands = (List<String>) value.getNode("commands").getValue();

            this.plugin.getEventsConfig().getChatResponses().put((String) key, new EventsConfig.ChatResponse(permission, regexes, responses, commands));
        });

        try {
            this.plugin.getCommandService().register(new YanamiBotMainCommand(), this.plugin);
            this.plugin.getLogger().debug("Initialized Commands");
        } catch (AnnotatedCommandException e) {
            e.printStackTrace();
        }

        this.plugin.getGame().getEventManager().registerListeners(plugin, new ChatEventListener());

    }

    public void reload() {
    }
}
