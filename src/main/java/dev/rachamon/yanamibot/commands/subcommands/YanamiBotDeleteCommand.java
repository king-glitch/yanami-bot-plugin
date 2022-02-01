package dev.rachamon.yanamibot.commands.subcommands;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.api.command.*;
import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import dev.rachamon.yanamibot.commands.elements.YanamiBotGetKeysCommandElement;
import dev.rachamon.yanamibot.utils.YanamiBotUtil;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * The type Yanami bot delete command.
 */
@ICommandAliases({"delete"})
@ICommandPermission("rachamonguilds.command.bot.delete")
@ICommandDescription("delete bot events")
public class YanamiBotDeleteCommand implements IPlayerCommand, IParameterizedCommand {
    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[]{
                new YanamiBotGetKeysCommandElement(Text.of("key"))
        };
    }

    @Nonnull
    @Override
    public CommandResult execute(@Nonnull Player source, @Nonnull CommandContext args) throws BotCommandException {

        Optional<String> key = args.getOne("key");
        if (!key.isPresent()) return CommandResult.empty();

        YanamiBot.getInstance().getBotManager().delete(key.get());
        source.sendMessage(YanamiBotUtil.toText(YanamiBot.getInstance().getLanguage().getCommandCategory().getCommandDeleteSuccessfully()));

        return CommandResult.success();
    }
}
