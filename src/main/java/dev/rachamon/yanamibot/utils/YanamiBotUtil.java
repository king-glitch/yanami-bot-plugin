package dev.rachamon.yanamibot.utils;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

/**
 * The type Yanami bot util.
 */
public class YanamiBotUtil {
    /**
     * To text text.
     *
     * @param string the string
     * @return the text
     */
    public static Text toText(String string) {
        return TextSerializers.FORMATTING_CODE.deserialize(string);
    }
}
