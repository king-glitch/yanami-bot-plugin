package dev.rachamon.yanamibot.commands.subcommands;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.api.command.*;
import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import dev.rachamon.yanamibot.commands.elements.YanamiBotGetKeysCommandElement;
import dev.rachamon.yanamibot.commands.elements.YanamiBotGetTypeCommandElement;
import dev.rachamon.yanamibot.utils.YanamiBotUtil;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

@ICommandAliases({"remove"})
@ICommandPermission("rachamonguilds.command.bot.remove")
@ICommandDescription("remove bot events")
public class YanamiBotRemoveCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{new YanamiBotGetKeysCommandElement(Text.of("key")), new YanamiBotGetTypeCommandElement(Text.of("type")), GenericArguments.integer(Text.of("content"))};
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws BotCommandException {

        Optional<String> key = args.getOne("key");
        Optional<String> type = args.getOne("type");
        Optional<Integer> content = args.getOne("content");

        if (!key.isPresent() || !type.isPresent() || !content.isPresent()) return CommandResult.empty();

        if (type.get().equalsIgnoreCase("command")) {
            YanamiBot.getInstance().getBotManager().removeChatCommand(key.get(), content.get());
        } else if (type.get().equalsIgnoreCase("response")) {
            YanamiBot.getInstance().getBotManager().removeChatResponse(key.get(), content.get());
        } else if (type.get().equalsIgnoreCase("regex")) {
            YanamiBot.getInstance().getBotManager().removeChatRegex(key.get(), content.get());
        }

        source.sendMessage(YanamiBotUtil.toText(YanamiBot.getInstance().getLanguage().getCommandCategory().getCommandRemoveSuccessfully()));


        return CommandResult.success();
    }
}
