package dev.rachamon.yanamibot.configs;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 * The type Language config.
 */
@ConfigSerializable
public class LanguageConfig {

    /**
     * Instantiates a new Language config.
     */
    public LanguageConfig() {
    }

    /**
     * The General category.
     */
    @Setting(value = "general", comment = "General Settings")
    protected LanguageGeneralCategory generalCategory = new LanguageGeneralCategory();

    /**
     * The Command category.
     */
    @Setting(value = "commands", comment = "General Settings")
    protected LanguageCommandCategory commandCategory = new LanguageCommandCategory();

    /**
     * The Question category.
     */
    @Setting(value = "question", comment = "General Settings")
    protected QuestionCategory questionCategory = new QuestionCategory();

    /**
     * Gets general category.
     *
     * @return the general category
     */
    public LanguageGeneralCategory getGeneralCategory() {
        return generalCategory;
    }

    /**
     * Gets command category.
     *
     * @return the command category
     */
    public LanguageCommandCategory getCommandCategory() {
        return commandCategory;
    }

    /**
     * Gets question category.
     *
     * @return the question category
     */
    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    /**
     * The type Language general category.
     */
    @ConfigSerializable
    public static class LanguageGeneralCategory {
        /**
         * The Prefix.
         */
        @Setting(comment = "plugin prefix", value = "prefix")
        protected String prefix = "&8[&d&l&oYanami&8] &f";

        /**
         * The Bot name.
         */
        @Setting(comment = "The bot name", value = "bot-name")
        protected String botName = "&d&l&oYanami";

        /**
         * The Click accept button.
         */
        @Setting(comment = "click button label", value = "click-accept-button")
        protected String clickAcceptButton = "&a&lAccept&r";

        /**
         * The Run commands question.
         */
        @Setting(comment = "run commands question", value = "run-commands-question")
        protected String runCommandsQuestion = "Would you like me to run you helper commands?&r";

        /**
         * The Message builder.
         */
        @Setting(comment = "message builder\n" +
                "variables : \n" +
                " - {bot-name} : the bot name\n" +
                " - {message} : bot response message\n" +
                " - {target} : target to response to", value = "message-builder")
        protected String messageBuilder = "&8[&4&lBOT&8] &8[{bot-name}&8] &a&l@{target}&f, &f{message}";

        /**
         * The Message builder raw.
         */
        @Setting(comment = "message raw builder\n" +
                "variables : \n" +
                " - {bot-name} : the bot name\n" +
                " - {message} : bot response message",
                value = "message-builder-raw")
        protected String messageBuilderRaw = "&8[&4&lBOT&8] &8[{bot-name}&8] &f";

        /**
         * The Message link placeholder.
         */
        @Setting(comment = "message placeholder when there is a link", value = "message-link-placeholder")
        protected String messageLinkPlaceholder = "&8[&a&o&lLink&8]";

        /**
         * The Message link on hover.
         */
        @Setting(comment = "message placeholder when there is a link", value = "message-link-on-hover")
        protected String messageLinkOnHover = "&a&oClick to redirect to&r &6&o&n{link}";

        /**
         * Gets bot name.
         *
         * @return the bot name
         */
        public String getBotName() {
            return botName;
        }

        /**
         * Gets bot name.
         *
         * @return the bot name
         */
        public String getMessageLinkPlaceholder() {
            return messageLinkPlaceholder;
        }

        /**
         * Gets message builder.
         *
         * @return the message builder
         */
        public String getMessageBuilder() {
            return messageBuilder;
        }

        /**
         * Gets prefix.
         *
         * @return the prefix
         */
        public String getPrefix() {
            return prefix;
        }

        /**
         * Gets click accept button.
         *
         * @return the click accept button
         */
        public String getClickAcceptButton() {
            return clickAcceptButton;
        }

        /**
         * Gets run commands question.
         *
         * @return the run commands question
         */
        public String getRunCommandsQuestion() {
            return runCommandsQuestion;
        }

        /**
         * Gets message builder raw.
         *
         * @return the message builder raw
         */
        public String getMessageBuilderRaw() {
            return messageBuilderRaw;
        }

        /**
         * Gets message link on hover.
         *
         * @return the message link on hover
         */
        public String getMessageLinkOnHover() {
            return messageLinkOnHover;
        }
    }

    /**
     * The type Language command category.
     */
    @ConfigSerializable
    public static class LanguageCommandCategory {
        /**
         * The Command add successfully.
         */
        @Setting(value = "command-add-successfully")
        protected String commandAddSuccessfully = "&fSuccessfully Added";
        /**
         * The Command remove successfully.
         */
        @Setting(value = "command-remove-successfully")
        protected String commandRemoveSuccessfully = "&fSuccessfully Removed";
        /**
         * The Command delete successfully.
         */
        @Setting(value = "command-delete-successfully")
        protected String commandDeleteSuccessfully = "&fSuccessfully Deleted";
        /**
         * The Command create successfully.
         */
        @Setting(value = "command-create-successfully")
        protected String commandCreateSuccessfully = "&fSuccessfully Created";
        /**
         * The Command reload successfully.
         */
        @Setting(value = "command-reload-successfully")
        protected String commandReloadSuccessfully = "&fSuccessfully Reload";
        /**
         * The Command set successfully.
         */
        @Setting(value = "command-set-successfully")
        protected String commandSetSuccessfully = "&fSuccessfully Set";

        /**
         * Gets command set successfully.
         *
         * @return the command set successfully
         */
        public String getCommandSetSuccessfully() {
            return commandSetSuccessfully;
        }

        /**
         * Gets command reload successfully.
         *
         * @return the command reload successfully
         */
        public String getCommandReloadSuccessfully() {
            return commandReloadSuccessfully;
        }

        /**
         * Gets command create successfully.
         *
         * @return the command create successfully
         */
        public String getCommandCreateSuccessfully() {
            return commandCreateSuccessfully;
        }

        /**
         * Gets command delete successfully.
         *
         * @return the command delete successfully
         */
        public String getCommandDeleteSuccessfully() {
            return commandDeleteSuccessfully;
        }

        /**
         * Gets command remove successfully.
         *
         * @return the command remove successfully
         */
        public String getCommandRemoveSuccessfully() {
            return commandRemoveSuccessfully;
        }

        /**
         * Gets command add successfully.
         *
         * @return the command add successfully
         */
        public String getCommandAddSuccessfully() {
            return commandAddSuccessfully;
        }
    }

    /**
     * The type Question category.
     */
    @ConfigSerializable
    public static class QuestionCategory {
        /**
         * The Click to view.
         */
        @Setting(comment = "question click to view.", value = "click-to-view")
        protected String clickToView = "&aClick to View";

        /**
         * The Click to answer.
         */
        @Setting(comment = "question click to answer.", value = "click-to-answer")
        protected String clickToAnswer = "&aClick to Answer";

        /**
         * The Must be player.
         */
        @Setting(comment = "question click to view.", value = "must-be-player")
        protected String mustBePlayer = "&cYou must be player to answer this question";

        /**
         * The Already responded.
         */
        @Setting(comment = "question click to view.", value = "already-responded")
        protected String alreadyResponded = "&cYou have already responded to that question!";

        /**
         * The Accept button.
         */
        @Setting(comment = "accept button", value = "accept-button")
        protected String acceptButton = "&aAccept";

        /**
         * The Declined button.
         */
        @Setting(comment = "declined button", value = "declined-button")
        protected String declinedButton = "&cDeclined";

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
