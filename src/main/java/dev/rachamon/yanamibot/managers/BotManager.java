package dev.rachamon.yanamibot.managers;

import dev.rachamon.yanamibot.YanamiBot;
import dev.rachamon.yanamibot.api.exceptions.BotCommandException;
import dev.rachamon.yanamibot.configs.EventsConfig;
import dev.rachamon.yanamibot.utils.YanamiBotUtil;
import org.spongepowered.api.entity.living.player.Player;

import java.util.ArrayList;

/**
 * The type Bot manager.
 */
public class BotManager {

    /**
     * Gets chat response.
     *
     * @param key the key
     * @return the chat response
     * @throws BotCommandException the bot command exception
     */
    public EventsConfig.ChatResponse getChatResponse(String key) throws BotCommandException {
        EventsConfig.ChatResponse response = YanamiBot.getInstance().getEventsConfig().getChatResponses().get(key);
        if (response == null) throw new BotCommandException("That key doesn't exists.");
        return response;
    }

    /**
     * Add chat regex.
     *
     * @param key   the key
     * @param regex the regex
     * @throws BotCommandException the bot command exception
     */
    public void addChatRegex(String key, String regex) throws BotCommandException {
        EventsConfig.ChatResponse response = this.getChatResponse(key);
        response.getRegexes().add(regex);
        this.save();
    }

    /**
     * Add chat command.
     *
     * @param key     the key
     * @param command the command
     * @throws BotCommandException the bot command exception
     */
    public void addChatCommand(String key, String command) throws BotCommandException {
        EventsConfig.ChatResponse response = this.getChatResponse(key);
        response.getCommands().add(command);
        this.save();
    }

    /**
     * Add chat response.
     *
     * @param key the key
     * @param res the res
     * @throws BotCommandException the bot command exception
     */
    public void addChatResponse(String key, String res) throws BotCommandException {
        EventsConfig.ChatResponse response = this.getChatResponse(key);
        response.getResponses().add(res);
        this.save();
    }

    /**
     * Sets chat permission.
     *
     * @param key        the key
     * @param permission the permission
     * @throws BotCommandException the bot command exception
     */
    public void setChatPermission(String key, String permission) throws BotCommandException {
        EventsConfig.ChatResponse response = this.getChatResponse(key);
        response.setPermission(permission);
        this.save();
    }

    /**
     * Remove chat regex.
     *
     * @param key     the key
     * @param content the content
     * @throws BotCommandException the bot command exception
     */
    public void removeChatRegex(String key, int content) throws BotCommandException {
        EventsConfig.ChatResponse response = this.getChatResponse(key);
        response.getRegexes().remove(content);
        this.save();
    }

    /**
     * Remove chat command.
     *
     * @param key     the key
     * @param content the content
     * @throws BotCommandException the bot command exception
     */
    public void removeChatCommand(String key, int content) throws BotCommandException {
        EventsConfig.ChatResponse response = this.getChatResponse(key);
        response.getCommands().remove(content);
        this.save();
    }

    /**
     * Remove chat response.
     *
     * @param key     the key
     * @param content the content
     * @throws BotCommandException the bot command exception
     */
    public void removeChatResponse(String key, int content) throws BotCommandException {
        EventsConfig.ChatResponse response = this.getChatResponse(key);
        response.getResponses().remove(content);
        this.save();
    }

    /**
     * Print chat response.
     *
     * @param source the source
     * @param key    the key
     * @throws BotCommandException the bot command exception
     */
    public void printChatResponse(Player source, String key) throws BotCommandException {
        EventsConfig.ChatResponse response = this.getChatResponse(key);
        String message = "" +
                "permission : " + response.getPermission() + "\n" +
                "regexes : \n" +
                String.join("\n", response.getRegexes()) + "\n" +
                "responses : \n" +
                String.join("\n", response.getResponses()) + "\n" +
                "commands : \n" +
                String.join("\n", response.getCommands()) + "\n";
        source.sendMessage(YanamiBotUtil.toText(message));
    }

    /**
     * Delete.
     *
     * @param key the key
     * @throws BotCommandException the bot command exception
     */
    public void delete(String key) throws BotCommandException {
        this.getChatResponse(key);
        YanamiBot.getInstance().getEventsConfig().getChatResponses().remove(key);
        this.save();
    }

    /**
     * Create.
     *
     * @param key the key
     * @throws BotCommandException the bot command exception
     */
    public void create(String key) throws BotCommandException {
        EventsConfig.ChatResponse response = YanamiBot.getInstance().getEventsConfig().getChatResponses().get(key);

        if (response != null) throw new BotCommandException("this key already exist");
        YanamiBot.getInstance().getEventsConfig().getChatResponses().put(key, new EventsConfig.ChatResponse("", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        this.save();

    }

    /**
     * Save.
     */
    public void save() {
        try {
            YanamiBot.getInstance().getEventsManager().save(
                    YanamiBot.getInstance().getEventsManager().getClazzType(),
                    YanamiBot.getInstance().getEventsConfig()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
