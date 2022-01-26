package dev.rachamon.yanamibot.commands.subcommands;

import dev.rachamon.yanamibot.api.command.*;
import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@ICommandAliases({"reload"})
@ICommandPermission("rachamonguilds.command.bot.reload")
@ICommandDescription("reload bot events")
public class YanamiBotReloadCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[0];
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws BotCommandException {
        return CommandResult.success();
    }
}
