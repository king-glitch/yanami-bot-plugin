package dev.rachamon.yanamibot.api.command;

import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

/**
 * The interface Player command.
 */
public interface IPlayerCommand extends CommandExecutor {
    @Override
    @Nonnull
    default CommandResult execute(@Nonnull CommandSource src, @Nonnull CommandContext args) throws BotCommandException {
        if (!(src instanceof Player)) {
            throw new BotCommandException("Must be in-game to execute this command.");
        }

        return execute((Player) src, args);
    }

    /**
     * Execute command result.
     *
     * @param source the source
     * @param args   the args
     * @return the command result
     * @throws BotCommandException the guild command exception
     */
    @Nonnull
    CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws BotCommandException;
}
