package dev.rachamon.yanamibot.listeners;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.configs.EventsConfig;
import dev.rachamon.yanamibot.utils.YanamiBotUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ChatEventListener {

    private final YanamiBot plugin = YanamiBot.getInstance();

    @Listener
    public void onChat(MessageChannelEvent.Chat event, @Root Player player) {
        List<EventsConfig.ChatResponse> responses = new ArrayList<>(this.plugin.getEventsConfig().getChatResponses().values());

        Optional<EventsConfig.ChatResponse> firstMatch = responses.stream().filter(m -> m.getRegexes().stream().anyMatch(a -> event.getRawMessage().toPlain().matches(a))).findFirst();

        if (!firstMatch.isPresent()) return;

        boolean hasPermission = player.hasPermission(firstMatch.get().getPermission());

        if (!hasPermission) return;

        Sponge.getScheduler().createTaskBuilder()
                .execute(() -> {
                    if (firstMatch.get().getCommands().size() > 0) {
                        firstMatch.get().getCommands().forEach(command -> {
                            this.plugin.getGame().getCommandManager().process(Sponge.getServer().getConsole(), command);
                        });

                        player.sendMessage(YanamiBotUtil.toText(
                                this.plugin.getLanguage()
                                        .getGeneralCategory()
                                        .getMessageBuilder()
                                        .replaceAll("\\{target}", player.getName())
                                        .replaceAll("\\{bot-name}", this.plugin.getLanguage().getGeneralCategory().getBotName())
                                        .replaceAll("\\{message}", firstMatch.get().getResponses().get(new Random().nextInt(firstMatch.get().getResponses().size())))
                        ));
                    } else {
                        Sponge.getServer().getOnlinePlayers().forEach(p -> {
                            p.sendMessage(YanamiBotUtil.toText(
                                    this.plugin.getLanguage()
                                            .getGeneralCategory()
                                            .getMessageBuilder()
                                            .replaceAll("\\{target}", player.getName())
                                            .replaceAll("\\{bot-name}", this.plugin.getLanguage().getGeneralCategory().getBotName())
                                            .replaceAll("\\{message}", firstMatch.get().getResponses().get(new Random().nextInt(firstMatch.get().getResponses().size())))
                            ));
                        });
                    }
                })
                .delay(500, TimeUnit.MILLISECONDS)
                .submit(this.plugin);

    }

}
