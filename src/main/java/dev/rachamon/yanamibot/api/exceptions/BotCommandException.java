package dev.rachamon.yanamibot.api.exceptions;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.utils.YanamiBotUtil;
import org.spongepowered.api.command.CommandException;


public class BotCommandException extends CommandException {

    private static final YanamiBot plugin = YanamiBot.getInstance();

    public BotCommandException(String message) {
        super(YanamiBotUtil.toText(plugin.getLanguage().getGeneralCategory().getPrefix() + message));
    }


}
