package dev.rachamon.yanamibot.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Command aliases.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ICommandAliases {

    /**
     * Value string [ ].
     *
     * @return the string [ ]
     */
    String[] value();

}