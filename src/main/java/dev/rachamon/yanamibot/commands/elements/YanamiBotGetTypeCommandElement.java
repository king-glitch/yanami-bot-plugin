package dev.rachamon.yanamibot.commands.elements;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.ArgumentParseException;
import org.spongepowered.api.command.args.CommandArgs;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * The type Yanami bot get type command element.
 */
public class YanamiBotGetTypeCommandElement extends CommandElement {
    /**
     * Instantiates a new Yanami bot get type command element.
     *
     * @param key the key
     */
    public YanamiBotGetTypeCommandElement(@Nullable Text key) {
        super(key);
    }

    @Nullable
    @Override
    protected Object parseValue(CommandSource source, CommandArgs args) throws ArgumentParseException {
        return args.next();
    }

    @Override
    public List<String> complete(CommandSource src, CommandArgs args, CommandContext context) {
        return Arrays.asList("command", "regex", "response");
    }
}
