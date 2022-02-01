package dev.rachamon.yanamibot.commands.elements;

import dev.rachamon.yanamibot.YanamiBot;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Yanami bot get keys command element.
 */
public class YanamiBotGetKeysCommandElement extends CommandElement {
    /**
     * Instantiates a new Yanami bot get keys command element.
     *
     * @param key the key
     */
    public YanamiBotGetKeysCommandElement(@Nullable Text key) {
        super(key);
    }

    @Nullable
    @Override
    protected Object parseValue(CommandSource source, CommandArgs args) throws ArgumentParseException {
        return args.next();
    }

    @Override
    public List<String> complete(CommandSource src, CommandArgs args, CommandContext context) {
        return new ArrayList<>(YanamiBot.getInstance().getEventsConfig().getChatResponses().keySet());
    }
}
