package dev.rachamon.yanamibot.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.*;

@ConfigSerializable
public class EventsConfig {
    @Setting(value = "chat-responses", comment = "Chat Responses")
    protected Map<String, ChatResponse> chatResponses = defaultChatResponses();

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

        public ChatResponse() {
        }

        public ChatResponse(String permission, List<String> regexes, List<String> responses, List<String> commands) {
            this.permission = permission;
            this.responses = responses;
            this.regexes = regexes;
            this.commands = commands;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public List<String> getResponses() {
            return responses;
        }


        public List<String> getRegexes() {
            return regexes;
        }

        public List<String> getCommands() {
            return commands;
        }
    }

    public Map<String, ChatResponse> getChatResponses() {
        return chatResponses;
    }

    private Map<String, ChatResponse> defaultChatResponses() {
        Map<String, ChatResponse> list = new HashMap<>();
        list.put("hello", new ChatResponse("", Arrays.asList("(hello|hi)", "how are you"), Arrays.asList("Hello World", "Hi!"), new ArrayList<>()));
        return list;
    }

}
