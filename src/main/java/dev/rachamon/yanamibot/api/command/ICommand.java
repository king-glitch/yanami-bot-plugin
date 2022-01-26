package dev.rachamon.yanamibot.api.command;

import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import javax.annotation.Nonnull;

/**
 * The interface Command.
 */
public interface ICommand extends CommandExecutor {
    @Nonnull
    CommandResult execute(@Nonnull CommandSource source, @Nonnull CommandContext args) throws BotCommandException;
}
