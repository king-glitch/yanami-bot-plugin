package dev.rachamon.yanamibot.commands;

import dev.rachamon.api.sponge.implement.command.*;
import dev.rachamon.yanamibot.commands.subcommands.*;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nonnull;

/**
 * The type Yanami bot main command.
 */
@ICommandChildren({
        YanamiBotAddCommand.class,
        YanamiBotReloadCommand.class,
        YanamiBotRemoveCommand.class,
        YanamiBotSetCommand.class,
        YanamiBotInfoCommand.class,
        YanamiBotCreateCommand.class,
        YanamiBotDeleteCommand.class
})
@ICommandAliases({"bot", "yanamibot"})
@ICommandHelpText(title = "Main Bot Help", command = "help")
@ICommandPermission("yanamibot.command.base")
public class YanamiBotMainCommand implements IPlayerCommand {
    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) {
        return CommandResult.success();
    }
}
