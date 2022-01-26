package dev.rachamon.yanamibot.utils;

import dev.rachamon.yanamibot.YanamiBot;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.source.ConsoleSource;

import java.util.Arrays;

public class LoggerUtil {
    private final ConsoleSource console;
    private final YanamiBot plugin = YanamiBot.getInstance();

    /**
     * Instantiates a new Logger util.
     *
     * @param server the server
     */
    public LoggerUtil(Server server) {
        this.console = server.getConsole();
    }

    /**
     * Info.
     *
     * @param message the message
     */
    public void info(String message) {
        console.sendMessage(YanamiBotUtil.toText("&8[&4&lYanamiBot&8] [&bINFO&8]&7: &a" + message));
    }

    /**
     * Success.
     *
     * @param message the message
     */
    public void success(String message) {
        console.sendMessage(YanamiBotUtil.toText("&8[&4&lYanamiBot&8] [&aSUCCESS&8]&7: &a" + message));
    }

    /**
     * Error.
     *
     * @param message the message
     */
    public void error(String message) {
        console.sendMessage(YanamiBotUtil.toText("&8[&4&lYanamiBot&8] [&cERROR&8]&7: &a" + message));

    }

    /**
     * Warning.
     *
     * @param message the message
     */
    public void warning(String message) {
        console.sendMessage(YanamiBotUtil.toText("&8[&4&lYanamiBot&8] [&eWARNING&8]&7: &a" + message));
    }

    /**
     * Warning.
     *
     * @param message the message
     */
    public void warning(java.lang.StackTraceElement[] message) {
        console.sendMessage(YanamiBotUtil.toText("&8[&4&lYanamiBot&8] [&eWARNING&8]&7: &a" + Arrays.toString(message)));
    }

    /**
     * Raw.
     *
     * @param message the message
     */
    public void raw(String message) {
        console.sendMessage(YanamiBotUtil.toText(message));
    }

    /**
     * Debug.
     *
     * @param message the message
     */
    public void debug(String message) {
        if (plugin == null) return;
        if (plugin.getConfig() == null) return;
//        if (!plugin.getConfig().getRoot().getGeneralCategorySetting().isDebug()) return;
        console.sendMessage(YanamiBotUtil.toText("&8[&4&lYanamiBot&8] [&dDEBUG&8]&7: &a" + message));

    }
}