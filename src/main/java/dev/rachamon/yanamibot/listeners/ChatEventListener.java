package dev.rachamon.yanamibot.listeners;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.api.utils.ChatQuestion;
import dev.rachamon.yanamibot.api.utils.ChatQuestionAnswer;
import dev.rachamon.yanamibot.configs.EventsConfig;
import dev.rachamon.yanamibot.configs.LanguageConfig;
import dev.rachamon.yanamibot.utils.YanamiBotUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChatEventListener {

    private final YanamiBot plugin = YanamiBot.getInstance();

    @Listener()
    public void onChat(MessageChannelEvent.Chat event, @Root Player player) {

        YanamiBot.getInstance().getLogger().debug("message cancelled : " + event.isMessageCancelled());
        if (event.isMessageCancelled()) return;
        YanamiBot.getInstance().getLogger().debug("raw message : " + event.getRawMessage().toPlain());

        if (event.getRawMessage().toPlain().charAt(0) == '/') return;
        List<EventsConfig.ChatResponse> responses = new ArrayList<>(this.plugin.getEventsConfig().getChatResponses().values());

        Optional<EventsConfig.ChatResponse> firstMatch = responses.stream().filter(m -> m.getRegexes().stream().anyMatch(a -> event.getRawMessage().toPlain().toLowerCase().matches(a))).findFirst();

        if (!firstMatch.isPresent()) return;

        boolean hasPermission = firstMatch.get().getPermission().isEmpty() || player.hasPermission(firstMatch.get().getPermission());

        if (!hasPermission) return;

        Sponge.getScheduler().createTaskBuilder()
                .execute(() -> {
                    LanguageConfig language = plugin.getLanguage();

                    Sponge.getServer().getOnlinePlayers().forEach(p -> {
                        p.sendMessage(YanamiBotUtil.toText(
                                language.getGeneralCategory()
                                        .getMessageBuilder()
                                        .replaceAll("\\{target}", player.getName())
                                        .replaceAll("\\{bot-name}", language.getGeneralCategory().getBotName())
                                        .replaceAll("\\{message}", firstMatch.get().getResponses().get(new Random().nextInt(firstMatch.get().getResponses().size())))
                        ));
                    });

                    if (firstMatch.get().getCommands().size() > 0) {

                        ChatQuestion question = ChatQuestion.of(YanamiBotUtil.toText(language.getGeneralCategory()
                                        .getMessageBuilderRaw()
                                        .replaceAll("\\{target}", player.getName())
                                        .replaceAll("\\{bot-name}", this.plugin.getLanguage().getGeneralCategory().getBotName())
                                        .replaceAll("\\{message}", language.getGeneralCategory().getRunCommandsQuestion())))
                                .addAnswer(ChatQuestionAnswer.of(YanamiBotUtil.toText(language.getGeneralCategory().getClickAcceptButton()), target -> {
                                    firstMatch.get().getCommands().forEach(command -> {
                                        this.plugin.getGame().getCommandManager().process(Sponge.getServer().getConsole(), command.replaceAll("\\{player}", player.getName()));
                                    });
                                })).build();

                        question.setAlreadyResponse(YanamiBotUtil.toText(language.getQuestionCategory().getAlreadyResponded()));
                        question.setClickToAnswer(YanamiBotUtil.toText(language.getQuestionCategory().getClickToAnswer()));
                        question.setClickToView(YanamiBotUtil.toText(language.getQuestionCategory().getClickToView()));
                        question.setMustBePlayer(YanamiBotUtil.toText(language.getQuestionCategory().getMustBePlayer()));
                        question.pollChat(player);
                    }

                })
                .delay(500, TimeUnit.MILLISECONDS)
                .submit(this.plugin);

    }

}
