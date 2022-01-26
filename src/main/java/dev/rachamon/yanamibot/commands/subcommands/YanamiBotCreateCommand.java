package dev.rachamon.yanamibot.commands.subcommands;


import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.api.command.*;
import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

@ICommandAliases({"create"})
@ICommandPermission("rachamonguilds.command.bot.create")
@ICommandDescription("create bot events")
public class YanamiBotCreateCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("key"))
        };
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws BotCommandException {
        Optional<String> key = args.getOne("key");
        if (!key.isPresent()) return CommandResult.empty();

        YanamiBot.getInstance().getBotManager().create(key.get());

        return CommandResult.success();
    }
}
