package dev.rachamon.yanamibot.api.exceptions;

import dev.rachamon.api.sponge.util.TextUtil;
import dev.rachamon.yanamibot.YanamiBot;
import org.spongepowered.api.command.CommandException;


/**
 * The type Bot command exception.
 */
public class BotCommandException extends CommandException {

    private static final YanamiBot plugin = YanamiBot.getInstance();

    /**
     * Instantiates a new Bot command exception.
     *
     * @param message the message
     */
    public BotCommandException(String message) {
        super(TextUtil.toText(plugin.getLanguage().getGeneralCategory().getPrefix() + "&c" + message));
    }


}
