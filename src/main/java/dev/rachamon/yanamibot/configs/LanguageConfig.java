package dev.rachamon.yanamibot.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class LanguageConfig {

    @Setting(value = "general", comment = "General Settings")
    private final LanguageGeneralCategory generalCategory = new LanguageGeneralCategory();
    private final LanguageCommandCategory commandCategory = new LanguageCommandCategory();
    private final QuestionCategory questionCategory = new QuestionCategory();

    public LanguageGeneralCategory getGeneralCategory() {
        return generalCategory;
    }

    public LanguageCommandCategory getCommandCategory() {
        return commandCategory;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    @ConfigSerializable
    public static class LanguageGeneralCategory {
        @Setting(comment = "plugin prefix", value = "prefix")
        private final String prefix = "&8[&d&l&oYanami&8] &f";

        @Setting(comment = "The bot name", value = "bot-name")
        private final String botName = "&d&l&oYanami";

        @Setting(comment = "click button label", value = "click-accept-button")
        private final String clickAcceptButton = "&a&lAccept&r";

        @Setting(comment = "run commands question", value = "run-commands-question")
        private final String runCommandsQuestion = "Would you like me to run you helper commands?&r";

        @Setting(comment = "message builder\n" +
                "variables : \n" +
                " - {bot-name} : the bot name\n" +
                " - {message} : bot response message\n" +
                " - {target} : target to response to", value = "message-builder")
        private final String messageBuilder = "&8[&4&lBOT&8] &8[{bot-name}&8] &a&l@{target}&f, &f{message}";

        @Setting(comment = "message raw builder\n" +
                "variables : \n" +
                " - {bot-name} : the bot name\n" +
                " - {message} : bot response message\n" +
                " - {target} : target to response to", value = "message-builder-raw")
        private final String messageBuilderRaw = "&8[&4&lBOT&8] &8[{bot-name}&8] &f{message}";

        public String getBotName() {
            return botName;
        }

        public String getMessageBuilder() {
            return messageBuilder;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getClickAcceptButton() {
            return clickAcceptButton;
        }

        public String getRunCommandsQuestion() {
            return runCommandsQuestion;
        }

        public String getMessageBuilderRaw() {
            return messageBuilderRaw;
        }
    }

    @ConfigSerializable
    public static class LanguageCommandCategory {
        @Setting(value = "command-add-successfully")
        private final String commandAddSuccessfully = "&fSuccessfully Added";
        @Setting(value = "command-remove-successfully")
        private final String commandRemoveSuccessfully = "&fSuccessfully Removed";
        @Setting(value = "command-delete-successfully")
        private final String commandDeleteSuccessfully = "&ffSuccessfully Deleted";
        @Setting(value = "command-create-successfully")
        private final String commandCreateSuccessfully = "&ffSuccessfully Created";
        @Setting(value = "command-reload-successfully")
        private final String commandReloadSuccessfully = "&ffSuccessfully Reload";
        @Setting(value = "command-set-successfully")
        private final String commandSetSuccessfully = "&ffSuccessfully Set";

        public String getCommandSetSuccessfully() {
            return commandSetSuccessfully;
        }

        public String getCommandReloadSuccessfully() {
            return commandReloadSuccessfully;
        }

        public String getCommandCreateSuccessfully() {
            return commandCreateSuccessfully;
        }

        public String getCommandDeleteSuccessfully() {
            return commandDeleteSuccessfully;
        }

        public String getCommandRemoveSuccessfully() {
            return commandRemoveSuccessfully;
        }

        public String getCommandAddSuccessfully() {
            return commandAddSuccessfully;
        }
    }

    @ConfigSerializable
    public static class QuestionCategory {
        @Setting(comment = "question click to view.", value = "click-to-view")
        private final String clickToView = "&aClick to View";

        @Setting(comment = "question click to answer.", value = "click-to-answer")
        private final String clickToAnswer = "&aClick to Answer";

        @Setting(comment = "question click to view.", value = "must-be-player")
        private final String mustBePlayer = "&cYou must be player to answer this question";

        @Setting(comment = "question click to view.", value = "already-responded")
        private final String alreadyResponded = "&cYou have already responded to that question!";

        @Setting(comment = "accept button", value = "accept-button")
        private final String acceptButton = "&aAccept";

        @Setting(comment = "declined button", value = "declined-button")
        private final String declinedButton = "&cDeclined";

        /**
         * Gets already responded.
         *
         * @return the already responded
         */
        public String getAlreadyResponded() {
            return alreadyResponded;
        }

        /**
         * Gets must be player.
         *
         * @return the must be player
         */
        public String getMustBePlayer() {
            return mustBePlayer;
        }

        /**
         * Gets click to answer.
         *
         * @return the click to answer
         */
        public String getClickToAnswer() {
            return clickToAnswer;
        }

        /**
         * Gets click to view.
         *
         * @return the click to view
         */
        public String getClickToView() {
            return clickToView;
        }

        /**
         * Gets declined button.
         *
         * @return the declined button
         */
        public String getDeclinedButton() {
            return declinedButton;
        }

        /**
         * Gets accept button.
         *
         * @return the accept button
         */
        public String getAcceptButton() {
            return acceptButton;
        }
    }
}
