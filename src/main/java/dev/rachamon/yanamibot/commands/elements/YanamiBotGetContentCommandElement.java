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

public class YanamiBotGetContentCommandElement extends CommandElement {
    public YanamiBotGetContentCommandElement(@Nullable Text key) {
        super(key);
    }

    @Nullable
    @Override
    protected Object parseValue(CommandSource source, CommandArgs args) throws ArgumentParseException {
        return args.next();
    }

    @Override
    public List<String> complete(CommandSource src, CommandArgs args, CommandContext context) {

        if (args.size() < 4) return new ArrayList<String>();

        String key = args.get(1);
        String type = args.get(2);

        try {
            if (type.equalsIgnoreCase("regex")) {
                return YanamiBot.getInstance().getBotManager().getChatResponse(key).getRegexes();
            } else if (type.equalsIgnoreCase("command")) {
                return YanamiBot.getInstance().getBotManager().getChatResponse(key).getCommands();
            } else if (type.equalsIgnoreCase("response")) {
                return YanamiBot.getInstance().getBotManager().getChatResponse(key).getResponses();
            }
        } catch (Exception ignored) {

        }


        return new ArrayList<>();
    }
}
