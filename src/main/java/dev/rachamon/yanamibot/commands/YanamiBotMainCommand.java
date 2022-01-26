package dev.rachamon.yanamibot.commands;

import dev.rachamon.yanamibot.api.command.*;
import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import dev.rachamon.yanamibot.commands.subcommands.YanamiBotAddCommand;
import dev.rachamon.yanamibot.commands.subcommands.YanamiBotReloadCommand;
import dev.rachamon.yanamibot.commands.subcommands.YanamiBotRemoveCommand;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

@ICommandChildren({
        YanamiBotAddCommand.class,
        YanamiBotReloadCommand.class,
        YanamiBotRemoveCommand.class
})
@ICommandAliases({"bot", "yanamibot"})
@ICommandHelpText(title = "Main Bot Help", command = "help")
@ICommandPermission("yanamibot.command.base")
public class YanamiBotMainCommand implements IPlayerCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws BotCommandException {
        return CommandResult.success();
    }
}
