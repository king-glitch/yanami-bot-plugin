package dev.rachamon.yanamibot.commands.subcommands;


import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.api.command.*;
import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import dev.rachamon.yanamibot.commands.elements.YanamiBotGetKeysCommandElement;
import dev.rachamon.yanamibot.commands.elements.YanamiBotGetTypeCommandElement;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

@ICommandAliases({"set"})
@ICommandPermission("rachamonguilds.command.bot.set")
@ICommandDescription("set bot permission")
public class YanamiBotSetCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                new YanamiBotGetKeysCommandElement(Text.of("key")),
                new YanamiBotGetTypeCommandElement(Text.of("type")),
                GenericArguments.string(Text.of("permission")),
        };
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws BotCommandException {

        Optional<String> key = args.getOne("key");
        Optional<String> type = args.getOne("type");
        Optional<String> content = args.getOne("permission");

        if (!key.isPresent() || !type.isPresent() || !content.isPresent()) return CommandResult.empty();

        try {
            if (type.get().equalsIgnoreCase("permission")) {
                if (content.get().equalsIgnoreCase("unset")) {
                    YanamiBot.getInstance().getBotManager().setChatPermission(key.get(), content.get());
                } else {
                    YanamiBot.getInstance().getBotManager().setChatPermission(key.get(), "");
                }
            }
        } catch (Exception ignored) {

        }

        return CommandResult.success();
    }
}
