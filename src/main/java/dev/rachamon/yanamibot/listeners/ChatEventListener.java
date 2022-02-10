package dev.rachamon.yanamibot.listeners;

import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.api.sponge.util.chatquestion.ChatQuestion;
import dev.rachamon.api.sponge.util.chatquestion.ChatQuestionAnswer;
import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.configs.EventsConfig;
import dev.rachamon.yanamibot.configs.LanguageConfig;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Text.Builder;
import org.spongepowered.api.text.action.TextActions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * The type Chat event listener.
 */
public class ChatEventListener {

    private final YanamiBot plugin = YanamiBot.getInstance();

    /**
     * On chat.
     *
     * @param event  the event
     * @param player the player
     */
    @Listener
    public void onChat(MessageChannelEvent.Chat event, @Root Player player) {

        try {

            YanamiBot.getInstance().getLogger().debug("original message : " + event.getOriginalMessage().toPlain());
            YanamiBot.getInstance().getLogger().debug("match : " + event.getOriginalMessage().toPlain().matches("<(\\[[A-Z]] )?([a-zA-Z0-9])\\w+>.*"));
            YanamiBot.getInstance().getLogger().debug("raw message : " + event.getRawMessage().toPlain());

            if (event.isMessageCancelled() || !event.getOriginalMessage().toPlain().matches("<(\\[[A-Z]] )?([a-zA-Z0-9])\\w+>.*"))
                return;
            List<EventsConfig.ChatResponse> responses = new ArrayList<>(this.plugin.getEventsConfig().getChatResponses().values());

            Optional<EventsConfig.ChatResponse> firstMatch = responses.stream().filter(m -> m.getRegexes().stream().anyMatch(a -> event.getRawMessage().toPlain().toLowerCase().matches(a))).findFirst();

            if (!firstMatch.isPresent()) return;

            boolean hasPermission = firstMatch.get().getPermission().isEmpty() || player.hasPermission(firstMatch.get().getPermission());
            YanamiBot.getInstance().getLogger().debug("has permission : " + hasPermission);

            if (!hasPermission) return;

            Sponge.getScheduler().createTaskBuilder()
                    .execute(() -> {

                        LanguageConfig language = plugin.getLanguage();
                        String message = firstMatch.get().getResponses().get(
                                new Random().nextInt(firstMatch.get().getResponses().size())
                        );

                        Builder formatter = Text.builder();

                        TextUtil.toText(language.getGeneralCategory()
                                .getMessageBuilder()
                                .replaceAll("\\{target}", player.getName())
                                .replaceAll("\\{bot-name}", language.getGeneralCategory().getBotName())).applyTo(formatter);


                        String lastColor = "";
                        for (String arg : message.split(" ")) {
                            if (!TextUtil.getLastColor(arg).isEmpty()) {
                                lastColor = TextUtil.getLastColor(arg);
                            }

                            try {
                                Builder builder = TextUtil.toText(lastColor + language.getGeneralCategory().getMessageLinkPlaceholder() + "&r " + lastColor).toBuilder();
                                builder.onClick(TextActions.openUrl(new URL(TextUtil.stripColor('&', arg))));
                                builder.onHover(TextActions.showText(TextUtil.toText(language.getGeneralCategory().getMessageLinkOnHover().replaceAll("\\{link}", arg))));
                                builder.applyTo(formatter);
                            } catch (MalformedURLException ignored) {
                                TextUtil.toText(lastColor + arg + "&r " + lastColor).applyTo(formatter);
                            }

                        }

                        Sponge.getServer().getOnlinePlayers().forEach(p -> {
                            p.sendMessage(formatter.toText());
                        });

                        if (firstMatch.get().getCommands().size() > 0) {

                            ChatQuestion question = ChatQuestion.of(TextUtil.toText(language.getGeneralCategory()
                                            .getMessageBuilderRaw()
                                            .replaceAll("\\{target}", player.getName())
                                            .replaceAll("\\{bot-name}", this.plugin.getLanguage().getGeneralCategory().getBotName())
                                            .replaceAll("\\{message}", language.getGeneralCategory().getRunCommandsQuestion())))
                                    .addAnswer(ChatQuestionAnswer.of(TextUtil.toText(language.getGeneralCategory().getClickAcceptButton()), target ->
                                            firstMatch.get().getCommands().forEach(command ->
                                                    this.plugin.getGame().getCommandManager().process(Sponge.getServer().getConsole(), command.replaceAll("\\{player}", player.getName()))))).build();

                            question.setAlreadyResponse(TextUtil.toText(language.getQuestionCategory().getAlreadyResponded()));
                            question.setClickToAnswer(TextUtil.toText(language.getQuestionCategory().getClickToAnswer()));
                            question.setClickToView(TextUtil.toText(language.getQuestionCategory().getClickToView()));
                            question.setMustBePlayer(TextUtil.toText(language.getQuestionCategory().getMustBePlayer()));
                            question.pollChat(player);
                        }

                    })
                    .delay(500, TimeUnit.MILLISECONDS)
                    .submit(this.plugin);
        } catch (Exception e) {
            this.plugin.getLogger().debug(e.getMessage());
        }

    }

}
