package dev.rachamon.yanamibot.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.*;

/**
 * The type Events config.
 */
@ConfigSerializable
public class EventsConfig {
    /**
     * The Chat responses.
     */
    @Setting(value = "chat-responses", comment = "Chat Responses")
    protected Map<String, ChatResponse> chatResponses = defaultChatResponses();

    /**
     * The type Chat response.
     */
    @ConfigSerializable
    public static class ChatResponse {
        @Setting(value = "permission")
        private String permission;
        @Setting(value = "responses")
        private List<String> responses;
        @Setting(value = "regexes")
        private List<String> regexes;
        @Setting(value = "commands")
        private List<String> commands;

        /**
         * Instantiates a new Chat response.
         */
        public ChatResponse() {
        }

        /**
         * Instantiates a new Chat response.
         *
         * @param permission the permission
         * @param regexes    the regexes
         * @param responses  the responses
         * @param commands   the commands
         */
        public ChatResponse(String permission, List<String> regexes, List<String> responses, List<String> commands) {
            this.permission = permission;
            this.responses = responses;
            this.regexes = regexes;
            this.commands = commands;
        }

        /**
         * Gets permission.
         *
         * @return the permission
         */
        public String getPermission() {
            return permission;
        }

        /**
         * Sets permission.
         *
         * @param permission the permission
         */
        public void setPermission(String permission) {
            this.permission = permission;
        }

        /**
         * Gets responses.
         *
         * @return the responses
         */
        public List<String> getResponses() {
            return responses;
        }


        /**
         * Gets regexes.
         *
         * @return the regexes
         */
        public List<String> getRegexes() {
            return regexes;
        }

        /**
         * Gets commands.
         *
         * @return the commands
         */
        public List<String> getCommands() {
            return commands;
        }
    }

    /**
     * Gets chat responses.
     *
     * @return the chat responses
     */
    public Map<String, ChatResponse> getChatResponses() {
        return chatResponses;
    }

    private Map<String, ChatResponse> defaultChatResponses() {
        Map<String, ChatResponse> list = new HashMap<>();
        list.put("hello", new ChatResponse("", Arrays.asList("(hello|hi)", "how are you"), Arrays.asList("Hello World", "Hi!"), new ArrayList<>()));
        return list;
    }

}
